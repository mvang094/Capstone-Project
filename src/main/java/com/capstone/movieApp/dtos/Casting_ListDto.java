package com.capstone.movieApp.dtos;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.entities.Casting_List;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Casting_ListDto {

    private Long cast_id;
    private String castName, castImage;
    Set<MovieDto> movieCastDto = new HashSet<>();

    public Casting_ListDto(Casting_List castList){
        if(castList.getCast_id() != null)
            this.cast_id = castList.getCast_id();
        if(castList.getCastName() != null)
            this.castName = castList.getCastName();
        if(castList.getCastImage() != null)
            this.castImage = castList.getCastImage();
    }

    public Long getCast_id() {
        return cast_id;
    }

    public void setCast_id(Long cast_id) {
        this.cast_id = cast_id;
    }

    public String getCastName() {
        return castName;
    }

    public void setCastName(String castName) {
        this.castName = castName;
    }

    public String getCastImage() {
        return castImage;
    }

    public void setCastImage(String castImage) {
        this.castImage = castImage;
    }

    public Set<MovieDto> getMovieCastDto() {
        return movieCastDto;
    }

    public void setMovieCastDto(Set<MovieDto> movieCastDto) {
        this.movieCastDto = movieCastDto;
    }
}

