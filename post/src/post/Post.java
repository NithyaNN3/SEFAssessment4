
package post;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Post {

    private int postID; // Unique identifier for the post
    private String postTitle; // Title of the post
    private String postBody; // Body/content of the post
    private String[] postTags; // Tags associated with the post
    private String[] postTypes = {"Very Difficult", "Difficult", "Easy"}; // Types/categories of post difficulty
    private String[] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"}; // Levels of post urgency
    private ArrayList<String> postComments = new ArrayList<>(); // Comments associated with the post
    boolean iseasy = false; // Flag indicating if the post is categorized as easy
    boolean isdifficult = false; // Flag indicating if the post is categorized as difficult

    /**
     * Constructor to initialize a Post object with given parameters.
     */
    public Post(int postID, String postTitle, String postBody, String[] postTags, String[] postTypes, String[] postEmergency, ArrayList postComments) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postTypes = postTypes;
        this.postEmergency = postEmergency;
        this.postComments = postComments;
    }

    /**
     * Method to add a post, validating its parameters and conditions.
     * Returns true if the post is added successfully, false otherwise.
     */
    public boolean addPost() {
        // Validate post title length
        if (this.postTitle.length() < 10 || this.postTitle.length() > 250) {
            return false;
        }

        // Validate post title format
        if (!this.postTitle.substring(0, 5).matches("[a-zA-Z]{5}")) {
            return false;
        }

        // Validate post tags
        for (String tag : this.postTags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                return false;
            }
        }

        // Validate post type and body length
        for (String type : this.postTypes) {
            if ((this.postTags.length < 2 || this.postTags.length > 5) ||
                    ((this.postBody.length() < 250) ||
                            (type.equals("Easy") && this.postTags.length > 3))) {
                return false;
            } else if (type.equals("Very Difficult") || type.equals("Difficult")) {
                isdifficult = true;
                if (this.postBody.length() < 300) {
                    return false;
                }
            }
        }

        // Validate post emergency level
        for (String emergency : this.postEmergency) {
            if (iseasy && (emergency.equals("Immediately Needed") || emergency.equals("Highly Needed"))) {
                return false;
            }
            if (isdifficult && emergency.equals("Ordinary")) {
                return false;
            }
        }

        return true;
    }

    /**
     * Method to add a comment to the post, validating its parameters and conditions.
     * Returns true if the comment is added successfully, false otherwise.
     */
    public boolean addComment(String comment) {
        // Split comment into words and validate length and format
        String[] words = comment.split("\\s+");
        if (words.length < 4 || words.length > 10 || !Character.isUpperCase(words[0].charAt(0))) {
            return false;
        }

        // Validate comment addition based on post type and emergency level
        if ((checkType("Easy") || checkEmergency("Ordinary")) && this.postComments.size() < 3) {
            return false;
        }

        // Write comment to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("comments.txt", true))) {
            writer.write(this.postID + "\n");
            for (String comm : this.postComments) {
                writer.write(comm + "\n");
            }
            writer.write(comment + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Helper method to check if the post has a certain type.
     */
    private boolean checkType(String type) {
        for (String ptype : this.postTypes) {
            if (ptype.equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to check if the post has a certain emergency level.
     */
    private boolean checkEmergency(String emergency) {
        for (String emer : this.postEmergency) {
            if (emer.equals(emergency)) {
                return true;
            }
        }
        return false;
    }

}
