package model;

import java.util.ArrayList;
import java.util.List;

public class ReferralManager {
    private static ReferralManager instance;
    
    private List<Referral> pendingReferrals;
    
    private ReferralManager() {
        this.pendingReferrals = new ArrayList<>();
    }
    
    public static synchronized ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }
    
    public void addReferral(Referral referral) {
        pendingReferrals.add(referral);
        
        String csvLine = convertReferralToCsv(referral);
        
        CsvHandler.appendLine("referrals.csv", csvLine);
        
        System.out.println("Referral added successfully: " + referral.getReferralId());
    }
    
    private String convertReferralToCsv(Referral referral) {
        return CsvHandler.createCsvLine(
            referral.getReferralId(),
            referral.getPatientId(),
            referral.getReferringClinicianId(),
            referral.getReferredToClinicianId(),
            referral.getReferringFacilityId(),
            referral.getReferredToFacilityId(),
            referral.getReferralDate(),
            referral.getUrgencyLevel(),
            referral.getReferralReason(),
            referral.getClinicalSummary(),
            referral.getRequestedInvestigations(),
            referral.getStatus(),
            referral.getAppointmentId(),
            referral.getNotes(),
            referral.getCreatedDate(),
            referral.getLastUpdated()
        );
    }
    
    public List<Referral> getPendingReferrals() {
        return pendingReferrals;
    }
    
    public void loadExistingReferrals(List<Referral> referrals) {
        this.pendingReferrals.clear();
        this.pendingReferrals.addAll(referrals);
        System.out.println("Loaded " + referrals.size() + " existing referrals into ReferralManager");
    }
    
    public int getPendingReferralCount() {
        return pendingReferrals.size();
    }
}
