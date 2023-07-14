package by.gsu.yournet.model;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Comment() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Comment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        this.user = user;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getTimeAgo() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        long currentTimeMillis = currentTime.getTime();
        long createdAtMillis = createdAt.getTime();
        long timeDifferenceMillis = Math.abs(currentTimeMillis - createdAtMillis);
        long seconds = timeDifferenceMillis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (seconds < 60) {
            return "just now";
        } else if (minutes < 60) {
            if (minutes == 1) return minutes + " minute ago";
            return minutes + " minutes ago";
        } else if (hours < 24) {
            if (hours == 1) return hours + " hour ago";
            return hours + " hours ago";
        } else {
            if (days == 1) return days + " day ago";
            return days + " days ago";
        }
    }
}

