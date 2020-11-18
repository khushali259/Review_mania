package com.example.reviewmanager;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static String ip = "192.168.0.81";
    private static String port = "1433";
    private static String classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "MovieReviews";
    private static String user = "isha";
    private static String pwd = "iyrwagh";
    private static String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database;

    private Connection connection = null;

    public String connectToDb(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(classes);
            connection = DriverManager.getConnection(url, user, pwd);
            return "SUCCESS";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return e.getMessage().toString();
            //textView.setText("ERROR!");
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage().toString();
        }
    }

    public int loginUser(String name, String pswd){
        if(connection!=null){
            try {

                String query = "select * from Users where username=\'" + name + "\' and password=\'" + pswd + "\';";
                Log.v("TAG", query);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int count = 0;
                while(resultSet.next()){
                    count++;
                }
                if(count==0){
                    return 0;
                }else{
                    query = "select userid from Users where username=\'" + name + "\' and password=\'" + pswd + "\';";
                    Log.v("TAG", query);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(query);
                    int userid = 0;
                    while(resultSet.next()){
                        userid = resultSet.getInt(1);
                    }
                    return userid;
                }



            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }else{
            return -1;
        }
    }


    public int registerUser(String name, String pswd, String mobile_no, String email_id){
        if(connection!=null){
            try {
                String query = "select * from Users where username=\'" + name + "\';";
                Log.v("TAG", query);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                int count = 0;
                while(resultSet.next()){
                    count++;
                }
                if(count!=0){
                    return 0;
                }
                query = "insert into Users (username, password, mobile_no, email_id) values (\'" + name + "\', \'" + pswd + "\', \'" + mobile_no + "\', \'" + email_id + "\');";
                Log.v("TAG", query);
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.executeUpdate();

                preparedStatement.close();

                query = "select userid from Users where username=\'" + name + "\' and password=\'" + pswd + "\';";
                Log.v("TAG", query);
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                int userid = 0;
                while(resultSet.next()){
                    userid = resultSet.getInt(1);
                }
                return userid;
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }else{
            return -1;
        }
    }

    public ArrayList<Movie> listMovies(){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        if(connection!=null){
            try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from Movie");
                Log.v("TAG", "gettingId");
                while(resultSet.next()){
                    Log.v("TAG", resultSet.getString(2));
                    movies.add(new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5), resultSet.getInt(6)));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            Log.v("TAG", "Connection is null");
        }
        return movies;
    }

    public Movie getMovie(int movie_id){
        if(connection!=null){
            try {
                Movie movie = new Movie(0, "", "", 0, 0, 0);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from Movie where movie_id = " + movie_id + ";");
                Log.v("TAG", "gettingId");
                while(resultSet.next()){
                    movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5), resultSet.getInt(6));
                }
                return movie;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            Log.v("TAG", "Connection is null");
        }
        return null;
    }

    public ArrayList<Review> listMovieReviews(int movie_id){
        ArrayList<Review> reviews = new ArrayList<>();
        if(connection!=null){
            try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from Reviews where movie_id = " + movie_id + ";");
                Log.v("TAG", "gettingId");
                while(resultSet.next()){
                    Log.v("TAG", resultSet.getString(2));
                    reviews.add(new Review(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getFloat(4), resultSet.getInt(5)));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            Log.v("TAG", "Connection is null");
        }
        return reviews;
    }

    public void addNewReview(String name, String director, int year_of_release, String review, float rating, int userId){
        if(connection!=null){
            try {

                String query = "insert into Movie (movie_name, director, year_of_release, overall_rating, no_reviews) values (\'" + name + "\', \'" + director + "\', " + year_of_release + ", "  + rating + ", 1);";
                Log.v("TAG", query);
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                Log.v("TAG", "getId");
                getMovieId(name, review, rating, userId);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
        }
    }

    private void getMovieId(String name, String review, float rating, int userId){
        if(connection!=null){
            try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select movie_id from Movie where movie_name =\'" + name + "\';");
                int id = 0;
                Log.v("TAG", "gettingId");
                while(resultSet.next()){
                    id = resultSet.getInt(1);
                    Log.v("TAG", String.valueOf(id));
                }

                String query = "insert into Reviews (movie_id, review, rating, userid) values (" + id + ", \'" + review + "\', " + rating + ", " + userId + " );";
                Log.v("TAG", query);
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                Log.v("TAG", "done");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
        }
    }

    public void addReview(String name, String review, float rating, int userId){
        if(connection!=null){
            try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * from Movie where movie_name =\'" + name + "\';");
                int id = 0;
                int no_review=0;
                float overall_rating = 0;
                Log.v("TAG", "gettingId");
                while(resultSet.next()){
                    id = resultSet.getInt(1);
                    overall_rating = resultSet.getFloat(5);
                    no_review = resultSet.getInt(6);
                    Log.v("TAG", String.valueOf(id));
                }

                String query = "insert into Reviews (movie_id, review, rating, userid) values (" + id + ", \'" + review + "\', " + rating + ", " + userId + ");";
                Log.v("TAG", query);
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.executeUpdate();

                preparedStatement.close();


                int num_reviews = no_review + 1;
                overall_rating = (overall_rating*no_review + rating)/num_reviews;

                query = "update Movie set overall_rating = " + overall_rating + ", no_reviews = " + num_reviews + "where movie_name = \'" + name + "\';";
                Log.v("TAG", query);
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                Log.v("TAG", "done");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Movie> listMoviesById(int userId){
        if(connection!=null){
            try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select distinct movie_id from Reviews where userid = " + userId + ";");
                ArrayList<Movie> movies = new ArrayList<>();
                Log.v("TAG", "gettingId");
                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    Statement stmt = connection.createStatement();
                    ResultSet resSet = stmt.executeQuery("select * from Movie where movie_id = " + id + ";");
                    while(resSet.next()){

                        movies.add(new Movie(resSet.getInt(1), resSet.getString(2), resSet.getString(3), resSet.getInt(4), resSet.getFloat(5), resSet.getInt(6)));
                    }
                }
                return movies;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Review showReview(int userId, int movie_id){
        if(connection!=null){
            try {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from Reviews where userid = " + userId +  " and movie_id = " + movie_id + ";");
                Review review = new Review(0, 0, "", 0, 0);
                Log.v("TAG", "gettingId");
                while(resultSet.next()){
                    review = new Review(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getFloat(4), resultSet.getInt(5));
                }
                return review;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void editReview(String review, float rating, int review_id){
        if(connection!=null){
            try {
                String query = "update Reviews set review = \'" + review + "\', rating = " + rating + " where review_id = " + review_id + ";";
                Log.v("TAG", query);
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.executeUpdate();

                preparedStatement.close();
                Log.v("TAG", "done");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
