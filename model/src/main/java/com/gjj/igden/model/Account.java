package com.gjj.igden.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gjj.igden.utils.EntityId;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "account")
public class Account implements UserDetails, EntityId {

	private static final long serialVersionUID = 4103535806827715305L;
	private Long id;
    private String accountName;
    private String email;
    private String additionalInfo;
    private String password;
    private List<IWatchListDesc> descriptions;
    private Date creationDate;
    private boolean enabled;
    private Avatar avatar;
    @Transient
    private String role;

    private Set<Role> roles = new HashSet<>();

    public Account() {
    }

    public Account(Long id) {
		super();
		this.id = id;
	}


	public Account(String accountName, String email, String additionalInfo, String password,
                   List<IWatchListDesc> descriptions, Date creationDate) {
        this.accountName = accountName;
         this.email = email;
        this.additionalInfo = additionalInfo;
        this.password = password;
        this.descriptions = descriptions;
        this.creationDate = creationDate;
    }

    public Account(long id, String accountName, String email,
                   String additionalInfo, String password,
                   List<IWatchListDesc> dataSets, Date creationDate) {
        this.id = id;
        this.accountName = accountName;
        this.email = email;
        this.additionalInfo = additionalInfo;
        this.password = password;
        this.descriptions = dataSets;
        this.creationDate = creationDate;
    }

    public Account(String accountName, String email,
                   String additionalInfo) {
        this.accountName = accountName;
        this.email = email;
        this.additionalInfo = additionalInfo;
    }

    public Account(long id, String accountName, String email, String additionalInfo,
    		Date creationDate) {
        this.id = id;
        this.accountName = accountName;
        this.email = email;
        this.additionalInfo = additionalInfo;
        this.creationDate = creationDate;
    }

/*  public Account(String accountName, String email, String additionalInfo,
                 List<WatchListDesc> dataSets) {
    this(null, accountName, email, additionalInfo, dataSets);
  }*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_Id")
    @Override
	public Long getId() {
		// TODO Auto-generated method stub
    	return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

    public Account(int accountId, String accountName, String email, String additionalInfo) {
        this(accountId, accountName, email, additionalInfo, (Date) null);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "additional_info")
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }


    @Column(name = "account_name", unique = true)
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Transient
    public List<IWatchListDesc> getAttachedWatchedLists() {
        return descriptions;
    }

    public void setDescriptions(List<IWatchListDesc> descriptions) {
        this.descriptions = descriptions;
    }

    @Transient
    public List<IWatchListDesc> getDataSets() {
        return descriptions;
    }

    public void setDataSets(List<IWatchListDesc> dataSets) {
        this.descriptions = dataSets;
    }

    public void removeAllDescriptions() {
    	this.descriptions.removeAll(descriptions);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(getEmail(), accountName);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Account && ((Account) obj).getEmail().equals(this.getEmail()) &&
                Objects.equals(((Account) obj).accountName, this.accountName);
    }

    

    @Override
	public String toString() {
		return "Account [id=" + id + ", accountName=" + accountName + ", email=" + email + ", additionalInfo="
				+ additionalInfo + ", password=" + password + ", descriptions=" + descriptions + ", creationDate="
				+ creationDate + ", enabled=" + enabled + ", avatar=" + avatar + ", roles=" + roles + "]";
	}

	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_roles",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "role")})
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public void addRole(Role role) {
    	roles.add(role);
    }

    public void removeAllRole() {
    	this.getRoles().removeAll(roles);
    }

    @Override
    @Transient
    public String getUsername() {
        return getAccountName();
    }


    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }


    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }


    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval=true)
    @JoinColumn(name="avatar_id")
	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	@Transient
	public List<IWatchListDesc> getDescriptions() {
		return descriptions;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
