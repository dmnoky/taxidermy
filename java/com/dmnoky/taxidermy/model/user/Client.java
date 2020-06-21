package com.dmnoky.taxidermy.model.user;

import com.dmnoky.taxidermy.model.animal.Animal;
import com.dmnoky.taxidermy.model.other.Address;
import com.dmnoky.taxidermy.model.user.sub.AnimalCount;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="client")
@PrimaryKeyJoinColumn(name="id")
public class Client extends User {
    @Embedded
    private Address address;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(
            name="client_phone_numbers",
            joinColumns=@JoinColumn(name="id_client_pn", nullable = false)
    )
    @Column(name = "phone_numbers")
    private List<String> phoneNumbers;

    /*@LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="clients_animals",
            inverseJoinColumns=@JoinColumn(name="id_animals_c", referencedColumnName="id", nullable = false),
            joinColumns=@JoinColumn(name="id_clients_a", referencedColumnName="id", nullable = false))
    @MapKeyColumn(name = "hash_code", unique = true)
    private Map<HashCount, Animal> animalMap;*/

    /*@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker")
    private Set<Authority> authorities = new HashSet<>(0);

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }*/

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(
            name="client_notes",
            joinColumns=@JoinColumn(name="id_client_n", nullable = false)
    )
    private List<String> notes;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="clients_animals",
            inverseJoinColumns=@JoinColumn(name="id_animals_c", referencedColumnName="id", nullable = false),
            joinColumns=@JoinColumn(name="id_clients_a", referencedColumnName="id", nullable = false))
    private List<Animal> animalList;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(
            name="animal_count",
            joinColumns= @JoinColumn(name="id_client_ac", nullable = false))
    private List<AnimalCount> animalCounts;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<AnimalCount> getAnimalCounts() {
        return animalCounts;
    }

    public void setAnimalCounts(List<AnimalCount> animalCounts) {
        this.animalCounts = animalCounts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append(super.toString());
        sb.append("address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
