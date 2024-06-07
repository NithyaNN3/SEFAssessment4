package post;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class unittestcases {
    private ArrayList<String> comments;
    private String[] tags;
    private String body;
    private String[] type;
    private String[] emergency;

    /**
     * Test case for validating a post with all parameters within valid conditions.
     */
    @Test
    public void testValidPost() {
        // all parameters are within valid conditions
        body = "A post may have several comments. The addComment function can add a new comment under a post to a TXT file. In other words, each time, "
                + "only one comment is added to a Post using this method. However, the following conditions should be consideredwhen adding c";
        comments = new ArrayList<>();
        comments.add("comment 1");
        tags = new String[]{"tag1", "tag2"};
        type = new String[]{"Easy"};
        emergency = new String[]{"Ordinary"};

        // Create and test posts
        Post post = new Post(1, "thisis a title", body, tags, type, emergency, comments);
        assertTrue(post.addPost());

        body = "A post may have several comments. The addComment function can add a new comment under a post to a TXT file. In other words, each time, "
                + "only one comment is added to a Post using this method. However, the following conditions should be consideredwhen adding c";
        comments = new ArrayList<>();
        comments.add("comment 1");
        tags = new String[]{"tag1", "tag2", "tag3"};
        type = new String[]{"Easy"};
        emergency = new String[]{"Ordinary"};
        Post post2 = new Post(2, "thisis a title", body, tags, type, emergency, comments);
        assertTrue(post2.addPost());
    }

    /**
     * Test case for an invalid post title (too short).
     */
    @Test
    public void testInvalidTitle() {
        body = "A post may have several comments. The addComment function can add a new comment under a post to a TXT file. In other words, each time, "
                + "only one comment is added to a Post using this method. However, the following conditions should be consideredwhen adding c";
        comments = new ArrayList<>();
        comments.add("comment 1");
        // Test posts with invalid title length
        Post post = new Post(1, "Shor", body, new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, comments);
        assertFalse(post.addPost());

        Post post2 = new Post(1, "Sho", body, new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, comments);
        assertFalse(post2.addPost());
    }

    /**
     * Test case for validating post body length.
     */
    @Test
    public void testBodyLength() {
        comments = new ArrayList<>();
        comments.add("comment 1");
        // Test posts with invalid body length
        Post post = new Post(1, "Valid Title", "Short", new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, comments);
        assertFalse(post.addPost());

        body = "A post may have several comments. The addComment function can add a new comment under a post to a TXT file. In other words, each time, "
                + "only one comment is added to a Post using this method. However, the following conditions should be consideredwhen adding c";
        Post post2 = new Post(1, "Valid Title", "Short body", new String[]{"tag1", "tag2"}, new String[]{"Difficult"}, new String[]{"Highly Needed"}, comments);
        assertFalse(post2.addPost());
    }

    /**
     * Test case for an invalid number of tags.
     */
    @Test
    public void testInvalidNumberOfTags() {
        body = "A post may have several comments. The addComment function can add a new comment under a post to a TXT file. In other words, each time, "
                + "only one comment is added to a Post using this method. However, the following conditions should be consideredwhen adding c";
        comments = new ArrayList<>();
        comments.add("comment 1");
        // Test posts with invalid number of tags
        Post post = new Post(5, "Valid Title", body, new String[]{"tag1", "tag2", "tag3", "tag4"}, new String[]{"Easy"}, new String[]{"Ordinary"}, comments);
        assertFalse(post.addPost());

        Post post2 = new Post(5, "Valid Title", body, new String[]{"tag1", "tag2"}, new String[]{"Difficult"}, new String[]{"Highly Needed"}, comments);
        assertFalse(post2.addPost());
    }

    /**
     * Test case for an invalid emergency type.
     */
    @Test
    public void testInvalidEmergency() {
        body = "A post may have several comments. The addComment function can add a new comment under a post to a TXT file. In other words, each time, "
                + "only one comment is added to a Post using this method. However, the following conditions should be consideredwhen adding c";
        comments = new ArrayList<>();
        comments.add("comment 1");
        tags = new String[]{"tag1", "tag2", "tag3"};
        type = new String[]{"Very Difficult"};
        emergency = new String[]{"Ordinary"};
        // Test posts with invalid emergency type
        Post post = new Post(1, "Title", body, tags, type, emergency, comments);
        assertFalse(post.addPost());

        type = new String[]{"Difficult"};
        Post post2 = new Post(1, "Title", body, tags, type, emergency, comments);
        assertFalse(post.addPost());
    }

    /**
     * Test case for validating post title format.
     */
    @Test
    public void testInvalidTitleFormat() {
        comments = new ArrayList<>();
        comments.add("comment 1");
        // Test posts with invalid title format
        Post post = new Post(3, "##Shor", "Valid Body", new String[]{"tag1", "tag2"}, new String[]{}, new String[]{}, comments);
        assertFalse(post.addPost());

        Post post2 = new Post(3, "Shor55", "Valid Body", new String[]{"tag1", "tag2"}, new String[]{}, new String[]{}, comments);
        assertFalse(post2.addPost());
    }

    /**
     * Test case for validating adding a valid comment to a post.
     */
    @Test
    public void testValidComment() {
        body = "A post may have several comments. The addComment function can add a new comment under a post to a TXT file. In other words, each time, "
                + "only one comment is added to a Post using this method. However, the following conditions should be consideredwhen adding c";
        String[] validComments = {
                "This is a valid comment.",
                "Another valid comment with more than 4 words.",    };

        Post post1 = new Post(1, "Title", body, new String[]{"tag1", "tag2"}, new String[]{"Difficult"}, new String[]{"Highly Needed"}, new ArrayList<String>());

        // Test adding valid comments to the post
        for (String comment : validComments) {
            assertTrue(post1.addComment(comment));
        }
    }

    /**
     * Test case for validating a comment with too few words.
     */
    @Test
    public void testLessWords() {
        body = "A post may have several comments. The addComment function can add a new comment under a post to a TXT file. In other words, each time, "
                + "only one comment is added to a Post using this method. However, the following conditions should be consideredwhen adding c";
        Post post1 = new Post(1, "Title", body, new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, new ArrayList<String>());
        // Test adding a comment with too few words
        assertFalse(post1.addComment("Too few words."));

        // Another test case with too few words
        Post post2 = new Post(1, "Title", body, new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, new ArrayList<String>());
        assertFalse(post2.addComment("Short."));
    }

    /**
     * Test case for validating a comment with too many words.
     */
    @Test
    public void testMoreWords() {
        body = "A post may have several comments. The addComment function can add a new comment under a post to a TXT file. In other words, each time, "
                + "only one comment is added to a Post using this method. However, the following conditions should be consideredwhen adding c";
        Post post1 = new Post(1, "Title", body, new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, new ArrayList<String>());
        // Test adding a comment with too many words
        assertFalse(post1.addComment("This comment has too many words and exceeds the limit."));

        // Another test case with too many words
        Post post2 = new Post(1, "Title", body, new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, new ArrayList<String>());
        assertFalse(post2.addComment("This is an excessively lengthy comment that is beyond the maximum word limit."));
    }

    /**
     * Test case for validating a comment with an invalid first letter.
     */
    @Test
    public void testInvalidFirstLetter() {
        // Test adding a comment with an invalid first letter
        Post post1 = new Post(1, "Title", "Body", new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, new ArrayList<String>());
        assertFalse(post1.addComment("invalid comment with lowercase first letter."));

        // Another test case with an invalid first letter
        Post post2 = new Post(1, "Title", "Body", new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, new ArrayList<String>());
        assertFalse(post2.addComment("incorrect comment with lowercase starting letter."));
    }

    /**
     * Test case for validating adding too many comments to a post.
     */
    @Test
    public void testTooManyComments() {
        // Test data 1
        ArrayList<String> comments1 = new ArrayList<>();
        comments1.add("Comment 1");
        comments1.add("Comment 2");
        comments1.add("Comment 3");
        Post post1 = new Post(1, "Title", "Body", new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, comments1);
        assertFalse(post1.addComment("New comment"));

        // Test data 2
        ArrayList<String> comments2 = new ArrayList<>();
        comments2.add("Comment 1");
        comments2.add("Comment 2");
        Post post2 = new Post(1, "Title", "Body", new String[]{"tag1", "tag2"}, new String[]{"Easy"}, new String[]{"Ordinary"}, comments2);
        assertFalse(post2.addComment("Another new comment."));
    }

    /**
     * Test case for validating adding too many comments to an ordinary post.
     */
    @Test
    public void testTooManyCommentsForOrdinaryPost() {
        // Test data 1
        ArrayList<String> comments1 = new ArrayList<>();
        comments1.add("Comment 1");
        comments1.add("Comment 2");
        comments1.add("Comment 3");
        Post post1 = new Post(1, "Title", "Body", new String[]{"tag1", "tag2"}, new String[]{"Difficult"}, new String[]{"Ordinary"}, comments1);
        assertFalse(post1.addComment("New comment"));

        // Test data 2
        ArrayList<String> comments2 = new ArrayList<>();
        comments2.add("Comment 1");
        comments2.add("Comment 2");
        Post post2 = new Post(1, "Title", "Body", new String[]{"tag1", "tag2"}, new String[]{"Difficult"}, new String[]{"Ordinary"}, comments2);
        assertFalse(post2.addComment("Another new comment."));
    }
}
       
