package com.paf.paperrex.TT.Service;

import com.paf.paperrex.TT.Entity.Profile;
import com.paf.paperrex.TT.Repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile getProfileById(Long id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        return optionalProfile.orElse(null);
    }

    public Profile updateProfile(Long id, Profile profile) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            profile.setProfileID(id);
            return profileRepository.save(profile);
        }
        return null; 
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}
