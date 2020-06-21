package com.dmnoky.taxidermy.model.user.sub;

import com.dmnoky.taxidermy.model.user.Worker;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article", referencedColumnName = "article", nullable = false)
    private Worker worker;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article", referencedColumnName = "article", nullable = false, updatable = false, insertable = false)
    private Client client;*/

    @Column(name = "authority")
    private String authority;

    public Authority() {
    }

    public Authority(Worker worker, String authority) {
        this.worker = worker;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
