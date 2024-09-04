package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "address_in_groups")
public class AddressInGroup {

    @Id
    @Column(name = "id")
    public int id;

    @Id
    @Column(name = "group_id")
    public int group_id;

    @Column(name = "deprecated")
    public Date deprecated = new Date();

    public AddressInGroup() {}

    public AddressInGroup(int id ,int group_id) {
        this.id = id;
        this.group_id = group_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressInGroup that = (AddressInGroup) o;
        return id == that.id && group_id == that.group_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group_id);
    }
}
