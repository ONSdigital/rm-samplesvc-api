package uk.gov.ons.ctp.response.sample.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import uk.gov.ons.ctp.common.FixtureHelper;
import uk.gov.ons.ctp.common.state.StateTransitionManager;
import uk.gov.ons.ctp.response.party.definition.Party;
import uk.gov.ons.ctp.response.party.representation.PartyDTO;
import uk.gov.ons.ctp.response.sample.definition.BusinessSurveySample;
import uk.gov.ons.ctp.response.sample.domain.model.CollectionExerciseJob;
import uk.gov.ons.ctp.response.sample.domain.model.SampleSummary;
import uk.gov.ons.ctp.response.sample.domain.model.SampleUnit;
import uk.gov.ons.ctp.response.sample.domain.repository.SampleSummaryRepository;
import uk.gov.ons.ctp.response.sample.domain.repository.SampleUnitRepository;
import uk.gov.ons.ctp.response.sample.message.PartyPublisher;
import uk.gov.ons.ctp.response.sample.representation.SampleSummaryDTO;
import uk.gov.ons.ctp.response.sample.representation.SampleSummaryDTO.SampleEvent;
import uk.gov.ons.ctp.response.sample.representation.SampleSummaryDTO.SampleState;
import uk.gov.ons.ctp.response.sample.representation.SampleUnitDTO;
import uk.gov.ons.ctp.response.sample.representation.SampleUnitDTO.SampleUnitEvent;
import uk.gov.ons.ctp.response.sample.representation.SampleUnitDTO.SampleUnitState;
import uk.gov.ons.ctp.response.sample.service.CollectionExerciseJobService;
import uk.gov.ons.ctp.response.sample.service.PartySvcClientService;

@RunWith(MockitoJUnitRunner.class)
public class SampleServiceImplTest {

  private static final String SAMPLEUNITTYPE = "H";

  private static final String SAMPLEUNITREF = "222";

  private static final String EXERCISEREF = "ref";

  private static final Timestamp EXERCISEDATETIME = Timestamp.valueOf("2017-09-08 00:00:00.0");

  @Mock
  private SampleSummaryRepository sampleSummaryRepository;

  @Mock
  private SampleUnitRepository sampleUnitRepository;

  @Mock
  private StateTransitionManager<SampleSummaryDTO.SampleState, SampleSummaryDTO.SampleEvent> sampleSvcStateTransitionManager;

  @Mock
  private StateTransitionManager<SampleUnitDTO.SampleUnitState, SampleUnitDTO.SampleUnitEvent> sampleSvcUnitStateTransitionManager;

  @Mock
  private PartySvcClientService partySvcClient;

  @Mock
  private PartyPublisher partyPublisher;

  @Mock
  private CollectionExerciseJobService collectionExerciseJobService;

  @InjectMocks
  private SampleServiceImpl sampleServiceImpl;

  private List<BusinessSurveySample> surveySample;
  private List<Party> party;
  private List<PartyDTO> partyDTO;
  private List<SampleUnit> sampleUnit;
  private List<SampleSummary> sampleSummaryList;
  private List<CollectionExerciseJob> collectionExerciseJobs;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    surveySample = FixtureHelper.loadClassFixtures(BusinessSurveySample[].class);
    party = FixtureHelper.loadClassFixtures(Party[].class);
    partyDTO = FixtureHelper.loadClassFixtures(PartyDTO[].class);
    sampleUnit = FixtureHelper.loadClassFixtures(SampleUnit[].class);
    sampleSummaryList = FixtureHelper.loadClassFixtures(SampleSummary[].class);
    collectionExerciseJobs = FixtureHelper.loadClassFixtures(CollectionExerciseJob[].class);

  }

  @Test
  public void verifySampleSummaryCreatedCorrectly() throws Exception {
    SampleSummary sampleSummary = sampleServiceImpl.createSampleSummary(surveySample.get(0));
    assertTrue(sampleSummary.getSurveyRef().equals("abc"));
    assertNotNull(sampleSummary.getIngestDateTime());
    assertTrue(sampleSummary.getEffectiveEndDateTime().getTime() == 1583743600000L);
    assertTrue(sampleSummary.getEffectiveStartDateTime().getTime() == 1483743600000L);
    assertTrue(sampleSummary.getState() == SampleSummaryDTO.SampleState.INIT);
  }

  @Test
  public void processSampleSummaryTest() throws Exception {
    BusinessSurveySample businessSample = surveySample.get(0);
    when(sampleSummaryRepository.save(any(SampleSummary.class))).then(returnsFirstArg());
    sampleServiceImpl.processSampleSummary(businessSample, businessSample.getSampleUnits().getBusinessSampleUnits());
    verify(sampleSummaryRepository).save(any(SampleSummary.class));
    verify(sampleUnitRepository).save(any(SampleUnit.class));
    verify(partyPublisher).publish(any(Party.class));
  }

  @Test
  public void sendToPartyServiceTestStateTransitions() throws Exception {
    when(partySvcClient.postParty(any())).thenReturn(partyDTO.get(0));
    when(sampleUnitRepository.findBySampleUnitRefAndType(SAMPLEUNITREF, SAMPLEUNITTYPE)).thenReturn(sampleUnit.get(0));
    when(sampleSvcUnitStateTransitionManager.transition(SampleUnitState.INIT, SampleUnitEvent.PERSISTING))
        .thenReturn(SampleUnitState.PERSISTED);
    when(sampleSummaryRepository.findOne(1)).thenReturn(sampleSummaryList.get(0));
    when(sampleSvcStateTransitionManager.transition(SampleState.INIT, SampleEvent.ACTIVATED))
        .thenReturn(SampleState.ACTIVE);

    sampleServiceImpl.sendToPartyService(party.get(0));

    assertThat(sampleUnit.get(0).getState(), is(SampleUnitState.PERSISTED));
    assertThat(sampleSummaryList.get(0).getState(), is(SampleState.ACTIVE));
  }

  @Test
  public void sendToPartyServiceTestNotAllSampleUnitsPosted() throws Exception {
    when(partySvcClient.postParty(any())).thenReturn(partyDTO.get(0));
    when(sampleUnitRepository.findBySampleUnitRefAndType(SAMPLEUNITREF, SAMPLEUNITTYPE)).thenReturn(sampleUnit.get(0));
    when(sampleSvcUnitStateTransitionManager.transition(SampleUnitState.INIT, SampleUnitEvent.PERSISTING))
        .thenReturn(SampleUnitState.PERSISTED);
    when(sampleSummaryRepository.findOne(1)).thenReturn(sampleSummaryList.get(0));
    when(sampleSvcStateTransitionManager.transition(SampleState.INIT, SampleEvent.ACTIVATED))
        .thenReturn(SampleState.ACTIVE);
    when(sampleUnitRepository.getTotalForSampleSummary(1)).thenReturn(1);

    sampleServiceImpl.sendToPartyService(party.get(0));

    assertThat(sampleUnit.get(0).getState(), is(SampleUnitState.PERSISTED));
    assertThat(sampleSummaryList.get(0).getState(), not(SampleState.ACTIVE));
  }

  @Test
  public void testCollectionExerciseJobIsStoredWhenSampleUnitsAreFound() throws Exception {
    List<SampleUnit> sampleUnits= new ArrayList<>();
    SampleUnit sampleUnitB = SampleUnit.builder().sampleUnitPK(1).sampleSummaryFK(1).sampleUnitRef(SAMPLEUNITREF).sampleUnitType(SAMPLEUNITTYPE).formType("Cuft").state(SampleUnitState.INIT).build();
    sampleUnits.add(sampleUnitB);
    when(sampleSummaryRepository.findBySurveyRefAndEffectiveStartDateTimeAndState(EXERCISEREF, EXERCISEDATETIME,
        SampleState.ACTIVE)).thenReturn(sampleSummaryList);
    when(sampleUnitRepository.findBySampleSummaryFK(1)).thenReturn(sampleUnits);
    Integer sampleUnitsTotal = sampleServiceImpl.initialiseCollectionExerciseJob(collectionExerciseJobs.get(0));
    verify(collectionExerciseJobService, times(1)).storeCollectionExerciseJob(any());
    assertThat(sampleUnitsTotal, is(1));
  }

  @Test
  public void testNoCollectionExerciseStoredWhenNoSampleUnits() throws Exception {
    Integer sampleUnitsTotal = sampleServiceImpl.initialiseCollectionExerciseJob(collectionExerciseJobs.get(0));
    verify(collectionExerciseJobService, times(0)).storeCollectionExerciseJob(any());
    assertThat(sampleUnitsTotal, is(0));
  }

}
