package at.rewe.assignment.entity;

import at.rewe.assignment.utility.ListStringConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String sender;

    @Column(nullable = false, length = 200)
    private String subject;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Convert(converter = ListStringConverter.class)
    private List<String> recipients = new ArrayList<>();

    public String getDomain() {
        String[] parts = sender.split("@");
        return parts[1];
    }
}