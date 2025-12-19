package com.rwandamedia.dao;

import com.rwandamedia.system.InMemoryDatabase;
import com.rwandamedia.models.Advertisement;
import java.util.List;

public class AdvertisementDAO {
    
    public List<Advertisement> getAllAdvertisements() {
        return InMemoryDatabase.getAllAdvertisements();
    }
    
    public boolean addAdvertisement(Advertisement advertisement) {
        System.out.println("Advertisement added: " + advertisement.getTitle());
        return true;
    }
    
    public boolean updateAdvertisement(Advertisement advertisement) {
        System.out.println("Advertisement updated: " + advertisement.getTitle());
        return true;
    }
    
    public boolean deleteAdvertisement(int advertisementID) {
        System.out.println("Advertisement deleted: " + advertisementID);
        return true;
    }
    
    public Advertisement getAdvertisementById(int advertisementID) {
        List<Advertisement> advertisements = getAllAdvertisements();
        for (Advertisement ad : advertisements) {
            if (ad.getAdvertisementID() == advertisementID) {
                return ad;
            }
        }
        return null;
    }
}