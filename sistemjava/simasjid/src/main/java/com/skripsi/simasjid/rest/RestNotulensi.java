package com.skripsi.simasjid.rest;

import com.skripsi.simasjid.model.ModelNotulensi;
import com.skripsi.simasjid.model.ModelPekerjaan;
import com.skripsi.simasjid.services.ServiceNotulensi;
import com.skripsi.simasjid.services.ServicePekerjaan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/notulensi")
public class RestNotulensi {

    @Autowired
    ServiceNotulensi serviceNotulensi;

    @GetMapping("/all")
    public List<ModelNotulensi> getAll() {
        return serviceNotulensi.findAll();
    }

    @RequestMapping(value = "/simpan", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public String simpanNotulensi(@RequestBody ModelNotulensi modelNotulensi){
        System.out.println("body : "+modelNotulensi.getIdNotulen());
        serviceNotulensi.save(modelNotulensi);
        return ""+modelNotulensi.getId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public String updateNotulensi(@RequestBody ModelNotulensi modelNotulensi){
        System.out.println("body : "+modelNotulensi.getIdNotulen());
        ModelNotulensi tempMn= serviceNotulensi.getOne(modelNotulensi.getId());
        modelNotulensi.setCreated(tempMn.getCreated());
        serviceNotulensi.save(modelNotulensi);
        return ""+modelNotulensi.getId();
    }

    @GetMapping("/hapus/{id}")
    public String getId(@PathVariable("id") final Integer id) {
        try{
            serviceNotulensi.deleteById(id);
            return "Berhasil";
        } catch (Exception e){
            return "Gagal";
        }
    }

}