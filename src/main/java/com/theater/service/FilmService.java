package com.theater.service;

import com.theater.entity.Film;
import com.theater.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    @Value("${upload.path}")
    private String uploadPath;


    public List<Film> getAll(){
        return  filmRepository.findAll();
    }

    public Film save(MultipartFile file, Film film) throws IOException {
        Film res = filmRepository.save(film);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String resultPic = "Img"+res.getId()+'.'+file.getOriginalFilename().split("\\.")[1];
            file.transferTo(new File(uploadPath + "/" + resultPic));
            res.setPic("images/"+resultPic);
            return filmRepository.save(res);
        }
        return null;
    }
    public Film delete(Film film){
        filmRepository.deleteById(film.getId());
        File temp_file = new File(uploadPath + film.getPic().substring(6));
        if (temp_file.delete()) System.out.println(temp_file.getName() + " is successfully deleted");
        else System.out.println("Failed to delete " + temp_file.getName() + " file");
        return film;
    }

    public Film get(int id){ return filmRepository.findById(id); }
}
