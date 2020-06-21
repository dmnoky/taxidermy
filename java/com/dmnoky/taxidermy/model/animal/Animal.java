package com.dmnoky.taxidermy.model.animal;

import com.dmnoky.taxidermy.model.animal.subcategory.Subsidiary;
import com.dmnoky.taxidermy.model.animal.subcategory.Type;
import com.dmnoky.taxidermy.model.product.Product;
import com.dmnoky.taxidermy.model.user.Client;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table( name="animal",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "article"})}
        )
public class Animal {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; // NN

    @OneToOne
    @JoinColumn(name="id_subsidiary")
    private Subsidiary subsidiary; // beast | bird | fish | other

    @OneToOne
    @JoinColumn(name="id_type")
    private Type type; // scarecrow | carpet | furs | bones | accessories

    @Column(name = "article", nullable = false, unique = true)
    private String article; // UN

    @Column(name = "number")
    private Integer number; // finished products

    @Column(name = "width")
    private Double width; // Bird overriding

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "price")
    private Double price;

    @Column(name = "deposit")
    private Double deposit;

    @Column(name = "content")
    private String content;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(
            name="animal_notes",
            joinColumns=@JoinColumn(name="id_animal_n", nullable = false)
    )
    private List<String> notes;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @JoinTable(
            name="animal_images",
            joinColumns=@JoinColumn(name="id_animal_i", nullable = false)
    )
    private List<byte[]> images;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="animals_products",
            joinColumns=@JoinColumn(name="id_animal_p", referencedColumnName="id", nullable = false),
            inverseJoinColumns=@JoinColumn(name="id_product_a", referencedColumnName="id", nullable = false))
    private List<Product> products;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="clients_animals",
            joinColumns=@JoinColumn(name="id_animals_c", referencedColumnName="id", nullable = false),
            inverseJoinColumns=@JoinColumn(name="id_clients_a", referencedColumnName="id", nullable = false))
    private List<Client> clients;

    @Transient
    private List<String> encodedImages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subsidiary getSubsidiary() {
        return subsidiary;
    }

    public void setSubsidiary(Subsidiary subsidiary) {
        this.subsidiary = subsidiary;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public List<BufferedImage> getImages() {
        List<BufferedImage> bufferedImages = new ArrayList<>();
        try {
            for (byte[] image : images) {
                bufferedImages.add(ImageIO.read(new ByteArrayInputStream(image)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException n) {
            return null;
        }
        return bufferedImages;
    }

    /*public void setImages(List<BufferedImage> images) {
        this.images = new ArrayList<>();
        for (BufferedImage image : images) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write( image, "jpg", baos );
                baos.flush();
                this.images.add(baos.toByteArray());
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }*/

    public void setImages(List<MultipartFile> images) {
        if (this.images == null) this.images = new ArrayList<>();
        for (MultipartFile image : images) {
            try {
                if (image.getBytes().length > 0)
                    this.images.add(image.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<String> getEncodedImages() {
        encodedImages = new ArrayList<>();
        for (byte[] img : this.images) {
            this.encodedImages.add(new String(Base64.encode(img)));
        }
        return encodedImages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equals(getId(), animal.getId()) &&
                Objects.equals(getName(), animal.getName()) &&
                Objects.equals(getSubsidiary(), animal.getSubsidiary()) &&
                Objects.equals(getType(), animal.getType()) &&
                Objects.equals(getArticle(), animal.getArticle()) &&
                Objects.equals(getNumber(), animal.getNumber()) &&
                Objects.equals(getWidth(), animal.getWidth()) &&
                Objects.equals(getHeight(), animal.getHeight()) &&
                Objects.equals(getWeight(), animal.getWeight()) &&
                Objects.equals(getPrice(), animal.getPrice()) &&
                Objects.equals(getDeposit(), animal.getDeposit()) &&
                Objects.equals(getContent(), animal.getContent()) &&
                Objects.equals(getNotes(), animal.getNotes()) &&
                Objects.equals(getImages(), animal.getImages()) &&
                Objects.equals(getProducts(), animal.getProducts()) &&
                Objects.equals(getClients(), animal.getClients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSubsidiary(), getType(), getArticle(), getNumber(), getWidth(), getHeight(), getWeight(), getPrice(), getDeposit(), getContent(), getNotes(), getImages(), getProducts(), getClients());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", subsidiary=").append(subsidiary);
        sb.append(", type=").append(type);
        sb.append(", article=").append(article);
        sb.append(", number=").append(number);
        sb.append(", price=").append(price);
        sb.append(", clients=").append(clients);
        sb.append(", products=").append(products);
        sb.append('}');
        return sb.toString();
    }
}
