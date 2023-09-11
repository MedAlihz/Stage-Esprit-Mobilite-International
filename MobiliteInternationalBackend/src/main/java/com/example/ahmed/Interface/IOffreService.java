package com.example.ahmed.Interface;


import com.example.ahmed.Entity.Offre;

import java.util.List;

public interface IOffreService {
    public Offre addOffre(Offre O);
    public void removeOffre(Integer id);
    public List<Offre> showOffre();
    //public Offre updateOffre(Offre f);

    public Offre getOffrebyId(Integer id);

}
