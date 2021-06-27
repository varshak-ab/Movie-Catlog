package com.vm.training.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vm.training.model.Movie;
import com.vm.training.model.Rating;
import com.vm.training.moviecatlog.CatlogItem;



@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatlogItem> getCatalog(@PathVariable("userId") String userId) {
		//1. get all the rated movie ids freom the ratings service
		List<Rating> ratingsBag = Arrays.asList(  //mocking call to ratings ms
				new Rating("movieOne",1),
				new Rating("movieTwo",2),
				new Rating("movieThree",3)
				);
		RestTemplate restTemplate = new RestTemplate();
		
		
		List<Rating> ratingsBag1 = Arrays.asList(
				new Rating("1234",4),
				new Rating("5678",3)				
				);

		//for each movie id retrieved from the previous MS call send it to MovieInfo and get movie details
		return ratingsBag.stream()                                                  //putting the list of items [emptying the bag on a] conveyor belt
				.map(rating -> {
					//Movie movie = restTemplate.getForObject("http://localhost:8083/movies/abdul", Movie.class);
					//return new CatlogItem(movie.getMovieName(), "description", rating.getRating());              				   //converting each rating item moving on the conveyor belt TO  catalogitem
					Movie movie = restTemplate.getForObject("http://localhost:8083/movies/"+rating.getMovieId(), Movie.class);
					return new CatlogItem(movie.getMovieName(), "description"+movie.getMovieId(), rating.getRating());              				   //converting each rating item moving on the conveyor belt TO  catalogitem

				})
				.collect(Collectors.toList());                                                                                   //after all the items have been converted put it back into the bag[list]
                                                                                  //after all the items have been converted put it back into the bag[list]

		//put them together and serve the user

		/*
		 * return Arrays.asList( new CatalogItem("firstmovie", "first movie desc", 1),
		 * new CatalogItem("secondmovie", "second movie desc", 2)
		 * 
		 * );
		 */

	}
}

