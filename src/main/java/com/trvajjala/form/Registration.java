package com.trvajjala.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.trvajjala.validation.EmailId;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class Registration implements Serializable {

    private static final long serialVersionUID = -3720364076838693670L;

    @Size(max = 35, min = 4)
    private String username;

    @Size(max = 50, min = 10)
    private String fullname;

    @EmailId(allowedCharacters = "[aA-zZ][0-1][_.]") // custom annotation to validate emailId
    private String email;

    @NotEmpty
    private String country;

    /** Observation: Validation framework requires getter/setter method */
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.country == null) ? 0 : this.country.hashCode());
        result = (prime * result) + ((this.email == null) ? 0 : this.email.hashCode());
        result = (prime * result) + ((this.fullname == null) ? 0 : this.fullname.hashCode());
        result = (prime * result) + ((this.username == null) ? 0 : this.username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Registration other = (Registration) obj;
        if (this.country == null) {
            if (other.country != null) {
                return false;
            }
        } else if (!this.country.equals(other.country)) {
            return false;
        }
        if (this.email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!this.email.equals(other.email)) {
            return false;
        }
        if (this.fullname == null) {
            if (other.fullname != null) {
                return false;
            }
        } else if (!this.fullname.equals(other.fullname)) {
            return false;
        }
        if (this.username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!this.username.equals(other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Registration [username=" + this.username + ", fullname=" + this.fullname + ", email=" + this.email + ", country=" + this.country + "]";
    }

}