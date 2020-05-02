package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		String user = "student";
		String pass = "student";
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT * FROM film WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, filmId);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getInt("release_year"));
			film.setLanguageId(rs.getInt("language_id"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
			film.setActors(findActorsByFilmId(filmId));

		}
		rs.close();
		ps.close();
		conn.close();

		return film;

	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;

		String user = "student";
		String pass = "student";

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, actorId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			actor = new Actor();
			actor.setId(rs.getInt(1));
			actor.setFirstName(rs.getString(2));
			actor.setLastName(rs.getString(3));
//		    actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
		}
		rs.close();
		ps.close();
		conn.close();
		
		return actor;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<>();
		String user = "student";
		String pass = "student";
		    Connection conn = DriverManager.getConnection(URL, user, pass);
		    String sql = "SELECT actor.* " + 
		    		"FROM actor JOIN film_actor ON film_actor.actor_id = actor.id " + 
		    		"JOIN film ON film.id = film_actor.film_id WHERE film_id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, filmId);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		    	int actorId = rs.getInt("id");
		    	String actorFirstName = rs.getString("first_name");
		    	String actorLastName = rs.getString("last_name");
		    	Actor actor = new Actor(actorId, actorFirstName, actorLastName);
		    	actors.add(actor);
		    	
		    	
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		
		  return actors;
	}

}
