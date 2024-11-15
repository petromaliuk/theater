package com.theater.service;

import com.theater.entity.Feedback;
import com.theater.entity.Film;
import com.theater.entity.User;
import com.theater.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService {

    private FeedbackRepository feedbackRepository;

    private FilmRepository filmRepository;

    private UserRepository userRepository;

    public void addFeedbackToFilmById(Feedback feedback, User us, int filmId ) {
        try {
            if (feedback.getGrade() < 1 || feedback.getGrade() > 10) throw new RuntimeException("Invalid grade");
            Film film = filmRepository.findById(filmId);
            if(film==null) throw new RuntimeException("No film");
            User user = userRepository.findById(us.getId()).orElseThrow(() -> new RuntimeException("No user"));
            feedback.setFilm(film);
            feedback.setUser(user);
            feedbackRepository.save(feedback);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }

    }
    public List<Feedback> getAllByFilm(int filmId) {
        return feedbackRepository.findAllByFilmId(filmId);
    }

    public List<Feedback> getAllByUser(int userId) {
        return feedbackRepository.findAllByUserId(userId);
    }
}
