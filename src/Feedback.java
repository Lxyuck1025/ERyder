public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void analyseFeedback(boolean isConcatenation, String s1, String s2, String s3, String s4, String s5) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(s1, s2, s3, s4, s5);
        } else {
            completeFeedback = feedbackUsingStringBuilder(s1, s2, s3, s4, s5).toString();
        }
        
        checkFeedbackLength(this.completeFeedback);
        createReviewID(this.firstName, this.lastName, this.completeFeedback);
    }

    private String feedbackUsingConcatenation(String s1, String s2, String s3, String s4, String s5) {
        String concatenatedFeedback="";
        concatenatedFeedback+=s1 +" "+ s2  +" "+ s3  +" "+ s4  +" "+ s5;
        return concatenatedFeedback;
    }

    private StringBuilder feedbackUsingStringBuilder(String s1, String s2, String s3, String s4, String s5) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1).append(" ").append(s2).append(" ").append(s3).append(" ").append(s4).append(" ").append(s5);
        return sb;
    }

    private boolean checkFeedbackLength(String feedback) {
        if (feedback.length() > 500) {
            longFeedback = true;
            return longFeedback;
        } else {
            longFeedback = false;
            return longFeedback;
        }
    }

    private String createReviewID(String fName, String lName, String feedback) {
        reviewID= (fName + lName).substring(2,6).toUpperCase();
        reviewID+=completeFeedback.substring(10,15).toLowerCase();
        reviewID+=(completeFeedback.length());
        reviewID+="_";
        reviewID+=System.currentTimeMillis();
        reviewID=reviewID.replace(" ", "");
        return reviewID;
    }

    @Override
    public String toString() {
        return "User: " + firstName + " " + lastName + "\n" +
               "Email: " + email + "\n" +
               "Feedback: " + completeFeedback + "\n" +
               "Too Long: " + longFeedback + "\n" +
               "ID: " + reviewID;
    }

    public static void main(String[] args) {
        String s1 = "I was very satisfied with the service.";
        String s2 = "The e-Bike is quite comfortable to ride.";
        String s3 = "The battery life of the e-Bike is impressive.";
        String s4 = "The customer support was helpful and responsive.";
        String s5 = "I would recommend this e-Bike to my friends and family.";

        Feedback f = new Feedback("John", "Doe", "john.doe@example.com");
        f.analyseFeedback(false, s1, s2, s3, s4, s5);

        System.out.println(f.toString());
    }
}