package com.theater.entity;

public final class Views {

    public interface Id {}
    public interface IdName extends Id {}
    public interface FullFilm extends IdName{}
    public interface PartSeance extends IdName {}
    public interface FullSeance extends PartSeance {}
    //public interface FullFeedback extends FullSeance {}
    public interface PartTicket extends PartSeance {}
   // public interface FullProfile extends FullSeance, FullFilm {}

}
