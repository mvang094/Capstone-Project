package com.capstone.movieApp.entities;

import com.capstone.movieApp.dtos.Casting_ListDto;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "casting_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Casting_List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cast_id;

    @Column
    private String castFirst, castName, castImage;

    @ManyToMany
    @JoinTable(
            name = "Cast_Details",
            joinColumns = {@JoinColumn(name = "cast_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    Set<Movies> movieCast = new HashSet<>();

    public Casting_List(Casting_ListDto castListDto){
        if(castListDto.getCastName() != null)
            this.castName = castListDto.getCastName();
        if(castListDto.getCastImage() != null)
            this.castImage = castListDto.getCastImage();
    }
}
