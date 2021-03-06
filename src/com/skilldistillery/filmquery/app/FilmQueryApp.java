package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

//  private void test() throws SQLException {
////    Film film = db.findFilmById(1);
////    System.out.println(film);
////    List<Actor> actors = film.getActors();
////    for (Actor actor : actors) {
////		System.out.println(actor);
////	}
////    
//////    Actor actor = db.findActorById(1);
//////    System.out.println(actor);
////    
//////    List<Actor> actors = db.findActorsByFilmId(1);
//////    System.out.println(actors);
//	  
////	  String language = db.languageFromId(10);
////	  System.out.println(language);
//  }

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		boolean exit = false;

		while (!exit) {
			System.out.println("Menu:");
			System.out.println("1) Look up a film by its id");
			System.out.println("2) Look up a film by a search keyword");
			System.out.println("3) Exit the application");
			System.out.print("Entry: ");
			int choice = input.nextInt();
			input.nextLine();
			
			switch (choice) {

			case 1:
				filmById(input);
				break;

			case 2:
				filmByKeyword(input);
				break;

			case 3:
				System.out.println("Exiting");
				exit = true;
			}
		}

	}

	private void filmById(Scanner input) throws SQLException {
		System.out.println("\nPlease enter a film ID: ");
		System.out.println("Entry: ");
		int filmId = input.nextInt();
		input.nextLine();
		Film film = db.findFilmById(filmId);

		if (film != null) {
			System.out.println(film + "\n");
			System.out.println("Actors in " + "\"" + film.getTitle() + "\":");
			List<Actor> actors = film.getActors();
			for (Actor actor : actors) {
				System.out.println("\t" + actor);
			}
			System.out.println();
		} else {
			System.out.println("No film is found with the ID of " + filmId);
		}

	}

	private void filmByKeyword(Scanner input) throws SQLException {
		System.out.println("\nPlease enter a film description: ");
		System.out.println("Entry: ");
		String filmDesc = input.next();

		List<Film> films = db.findFilmByKeyword(filmDesc);

		if (films.size() != 0) {
			for (Film film : films) {
				System.out.println(film + "\n");
				System.out.println("Actors in " + "\"" + film.getTitle() + "\":");

				List<Actor> actors = film.getActors();
				for (Actor actor : actors) {
					System.out.println("\t" + actor);
				}
				System.out.println();
			}
			System.out.println();

		} else {
			System.out.println("No film is found with the description of " + filmDesc);
		}

	}

}
