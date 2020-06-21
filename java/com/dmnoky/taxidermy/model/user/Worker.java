package com.dmnoky.taxidermy.model.user;

import com.dmnoky.taxidermy.model.user.sub.Authority;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="worker")
@PrimaryKeyJoinColumn(name="id")
public class Worker extends User {
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(
            name="worker_notes",
            joinColumns=@JoinColumn(name="id_worker_n", nullable = false)
    )
    @Column(name = "notes")
    private List<String> tasks;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker")
    private Set<Authority> authorities = new HashSet<>(0);

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }
}
