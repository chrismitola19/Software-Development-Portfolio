package corbos.fieldagent.service;

import corbos.fieldagent.data.AgencyRepository;
import corbos.fieldagent.data.CountryRepository;
import corbos.fieldagent.data.SecurityClearanceRepository;
import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Country;
import corbos.fieldagent.entities.SecurityClearance;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LookupService {

    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;

    public LookupService(AgencyRepository agencyRepo,
            CountryRepository countryRepo,
            SecurityClearanceRepository securityRepo) {
        this.agencyRepo = agencyRepo;
        this.countryRepo = countryRepo;
        this.securityRepo = securityRepo;
    }

    public List<Agency> findAllAgencies() {
        return agencyRepo.findAll();
    }

    public Agency findAgencyById(int agencyId) {
        return agencyRepo.findById(agencyId)
                .orElse(null);
    }

    public List<Country> findAllCountries() {
        return countryRepo.findAll();
    }

    public Country findCountryByCode(String countryCode) {
        return countryRepo.findById(countryCode)
                .orElse(null);
    }

    public List<SecurityClearance> findAllSecurityClearances() {
        return securityRepo.findAll();
    }

    public SecurityClearance findSecurityClearanceById(int securityClearanceId) {
        return securityRepo.findById(securityClearanceId)
                .orElse(null);
    }
}
