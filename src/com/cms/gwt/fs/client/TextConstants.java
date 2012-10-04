package com.cms.gwt.fs.client;

import com.google.gwt.i18n.client.Messages;

public interface TextConstants extends Messages {

	String ServiceTicketList();
	String ServiceTicket(String arg);
	String SkillsPanel(String arg);
	String DetailsPanel(String arg);
	String SchedulePanel(String arg);
	String WorkHistoryPanel(String arg);
	String BillingPanel(String arg);
	
	String TicketListClearSort();
	String TicketListTitle();
	String TicketListTicketNumber();
	String TicketListEventId();
	String TicketListCustomerCode();
	String TicketListCustomerName();
	String TicketListScheduleStartDate();
	String TicketListScheduleStartTime();
	String TicketListTypeDescription();
	String TicketListTimeEstimate();
	String TicketListDescription();
	String TicketListStatusDescription();
	
	String TicketCalendarTitle();
	
	String EventsList();
	
	String TicketDetailTicketNumber();
	String TicketDetailServiceType();
	String TicketDetailServiceItem();
	String TicketDetailDescription();
	String TicketDetailMainContact();
	String TicketDetailComplaint();
	String TicketDetailServiceProcedure();
	String TicketDetailStatus();
	String TicketDetailDateOpened();
	String TicketDetailMainContactNumber();
	String TicketDetailSiteContact();
	String TicketDetailSiteContactNumber();
	String TicketDetailSecondNumber();	
	String TicketDetailContactEmail();
	String TicketDetailRepeatIssue();
	String TicketDetailPreviousTicket();
	String TicketDetailPriorityCode();
	String TicketDetailSubject();
	String TicketDetailConfirmationNeeded();
	String TicketDetailConfirmed();
	String TicketDetailScheduledDate();
	String TicketDetailStartTime();
	String TicketDetailEffort();
	String TicketDetailAssignedTo();
	String TicketDetailRespondBy();
	String TicketDetailEstimatedCost();
	String TicketDetailCustomerPO();
	String TicketDetailMoreDetails();
	
	String ServiceItem();
	String ServiceItemProduct();
	String ServiceItemProductId();
	String ServiceItemShipped();
	String ServiceItemExpires();
	String ServiceItemContractType();
	String ServiceItemAssetType();
	String ServiceItemAssetNumber();
	String ServiceItemAssetDescription();
	
	String Location();
	String LocationCustomer();
	String LocationOr();
	String LocationCustomerSite();
	String LocationTerritory();
	String LocationInHousePlant();
	
	String AccessHours();
	String AccessHoursTimeZone();
	String AccessHoursDay();
	String AccessHoursStart1();
	String AccessHoursEnd1();
	String AccessHoursStart2();
	String AccessHoursEnd2();
	
	String Skills();
	String SkillsSkill();
	String SkillsDescription();
	String SkillsLevelRequired();
	String SkillsNotes();
	
	String Details();
	String DetailsFromProcedure();
	String DetailsWorkCode();
	String DetailsSequence();
	String DetailsDescription();
	String DetailsManPower();
	String DetailsTimeEstimate();
	String DetailsWarrantyTask();
	String DetailsStatus();
	String DetailsMaterial();
	String DetailsLineNumber();
	String DetailsPartNumber();
	String DetailsPartDescription();
	String DetailsQuantityRequired();
	String DetailsUnitOfMeasure();
	String DetailsProbability();
	String DetailsUsed();
	String DetailsNotes();
	
	String Schedule();
	String ScheduleEventId();
	String ScheduleScheduleType();
	String ScheduleManPower();
	String ScheduleTimeEstimate();
	String ScheduleTechnician();
	String ScheduleDescription();
	String ScheduleScheduledDate();
	String ScheduleScheduledTime();
	String ScheduleTimeZone();
	String ScheduleStatus();
	String ScheduleParentEventId();
	String ScheduleParentSplitType();
	String ScheduleScheduledStart();
	String ScheduleSpecialEventCode();
	String ScheduleTravelTimeEstimate();
	String ScheduleServiceCategory();
	String ScheduleOverrideRates();
	String ScheduleLabourRate();
	String ScheduleOvertimeRate();
	
	String WorkHistory();
	String WHSummary();
	String WHTicketNumber();
	String WHEventId();
	String WHCounterReading();
	String WHTechnician();
	String WHDate();
	String WHArrivalTime();
	String WHHoursReported();
	String WHMaterialReported();
	String WHNumberOfItems();
	String WHOtherReported();
	String WHNotes();
	String WHSolutionNotepad();
	String WHTimeSheetNotepad();
	String WHCounter();
	String WHCounterDescription();
	String WHTimeEntry();
	String WHMaterial();
	String WHComplete();
	String WHArrivalInfo();
	String WHArrivalDate();
	
	String TimeEntry();
	String TETicketNumber();
	String TEEventID();
	String TECounterReading();
	String TETechnician();
	String TEArrivalDate();
	String TEArrivalTime();
	String TETotalTimeRecorded();
	String TETotalHoursWorked();
	String TESequence();
	String TEDescription();
	String TELine();
	String TETime();
	String TEOvertime();
	String TEWarranty();
	String TEAddTime();
	String TEEstimate();
	String TEEntered();
	String TEActual();
	String TEIsPosted();
	
	String Material();
	String MatTicketNumber();
	String MatEventId();
	String MatCounterReading();
	String MatTechnician();
	String MatArrivalDate();
	String MatArrivalTime();
	String MatSequence();
	String MatPartDescription();
	String MatDescription();
	String MatLine();
	String MatPartNumber();
	String MatQuantity();
	String MatWarranty();
	String MatLotNumber();
	String MatSerialNumber();
	String MatRequired();
	String MatUnitOfMeasure();
	String MatRecorded();
	String MatUsed();
	String MatLotControlled();
	String MatSerialControlled();
	String MatAllMaterial();
	String MatCoveredByWarranty();
	String MatStockLocation();
	String MatBinLocation();
	String MatIsPosted();
	
	String Billing();
	String BillingLineNumber();
	String BillingType();
	String BillingDescription();
	String BillingPartNumber();
	String BillingLotNumber();
	String BillingSerialNumber();
	String BillingQuantity();
	String BillingUnitOfMeasure();
	String BillingUnitPrice();
	String BillingSubtotal();
	String BillingTaxes();
	String BillingTotal();
	String BillingWarranty();
	String BillingCompleted();
	String BillingUncompleted();
	String BillingLabour();
	String BillingParts();
	String BillingMiscellaneous();
	String BillingToPay();
	String BillingTotalToPay();
	String BillingChargeType();
	String BillingFromEventId();
	String BillingMiscellaneousChargeCode();
	String BillingTaxGroup();
	String BillingTaxRate();
	String BillingCalculate();
	
	String Notes();
	String NotesProblem();
	String NotesSolution();
	String NotesTimeSheet();
	
	String Day();
	String Week();
	String Month();
	String Today();
	String NumberOfEvents();
	
	String Options();
	String StartDate();
	String Horizon();
	
	String Close();
	String Back();
	String Add();
	String Update();
	String Delete();
	String View();
	String Cancel();
	String Accept();
	String Decline();
	String PageNum(String arg1, String arg2);
	String PageUp();
	String PageDown();
	String Save();
	String Clear();
	String Edit();
	String Remove();
	String OK();
	String Error();
	String Show();
	
	String GridDisplaying();
	String GridNoRecords();
	
	String Sunday();
	String Monday();
	String Tuesday();
	String Wednesday();
	String Thursday();
	String Friday();
	String Saturday();
	
	String Confirm(String arg1, String arg2, String arg3);
	String NotesSaveConfirm();

	String TicketHeaderServiceType1();
	String TicketHeaderServiceType2();
	
	String DescNotAvail();
	
	String UpdateScheduledEventFailure();
	String AddScheduledEventFailure();
	String DeleteScheduledEventFailure();	
	String DeleteScheduledEventNotif(String arg1);
	
	String UpdateBillingDetailFailure();
	String AddBillingDetailFailure();
	String DeleteBillingDetailFailure();	
	String DeleteBillingDetailNotif(String arg1);
	String CalculateBillingDetailFailure();
	
	String UpdateEventStatusFailure();
	String UpdateEventStatusNotif(String arg1);
	
	String SaveWHCounterFailure();
	
	String CompleteScheduleFailure();
	
	String DeleteRecordedLabourNotif();
	String DeleteRecordedLabourFailure();
	String SaveRecordedLabourListNotif();
	String SaveRecordedLabourListFailure();
	
	String DeleteRecordedMaterialListEntryNotif();
	String DeleteRecordedMaterialListEntryFailure();
	
	String UpdateRecordedMaterialFailure();	
	
	String SaveRecordedMaterialNotif();
	String SaveRecordedMaterialFailure();

	String SaveArrivalInfoFailure();
	
	String AddRecordedLabourFailure();
	
	String SaveNotesPageFailure();
	
	String InvalidIntInput(String arg1);
	
	String MessageElement();
	
	String DialogTitleWarning();
	String DialogTitleNotif();
	String DialogTitleError();
	
	String SignOut();
	String Help();
	String About();
	String Greeting();
	
	String DialogAboutTitle();
	
	String InternalErrorMsg();
	
	String AppName();
}


