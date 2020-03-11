package com.suse.cap.carrenting.data;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Car {
	@Id
	@Column(length = 60)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private String title;
	@Column(length = 2000)
	private String description;
	private boolean isRented;
	@Lob
	private byte[] photo;
	private String photoAlt;
	@Column(length = 2000)
	private String rentedPersonFirstName;
	@Column(length = 2000)
	private String rentedPersonLastName;
	@Column(length = 2000)
	private String rentedPersonContactNumber;
	@Column(length = 2000)
	private String rentedPersonContactEmail;

	public Car() {
	}

	public Car(String id, String title, String description, boolean isRented, byte[] photo, String photoAlt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.isRented = isRented;
		this.photo = photo;
		this.photoAlt = photoAlt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRented() {
		return isRented;
	}

	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPhotoAlt() {
		return photoAlt;
	}

	public void setPhotoAlt(String photoAlt) {
		this.photoAlt = photoAlt;
	}

	public String getRentedPersonFirstName() {
		return rentedPersonFirstName;
	}

	public void setRentedPersonFirstName(String rentedPersonFirstName) {
		this.rentedPersonFirstName = rentedPersonFirstName;
	}

	public String getRentedPersonLastName() {
		return rentedPersonLastName;
	}

	public void setRentedPersonLastName(String rentedPersonLastName) {
		this.rentedPersonLastName = rentedPersonLastName;
	}

	public String getRentedPersonContactNumber() {
		return rentedPersonContactNumber;
	}

	public void setRentedPersonContactNumber(String rentedPersonContactNumber) {
		this.rentedPersonContactNumber = rentedPersonContactNumber;
	}

	public String getRentedPersonContactEmail() {
		return rentedPersonContactEmail;
	}

	public void setRentedPersonContactEmail(String rentedPersonContactEmail) {
		this.rentedPersonContactEmail = rentedPersonContactEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isRented ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result + ((photoAlt == null) ? 0 : photoAlt.hashCode());
		result = prime * result + ((rentedPersonContactEmail == null) ? 0 : rentedPersonContactEmail.hashCode());
		result = prime * result + ((rentedPersonContactNumber == null) ? 0 : rentedPersonContactNumber.hashCode());
		result = prime * result + ((rentedPersonFirstName == null) ? 0 : rentedPersonFirstName.hashCode());
		result = prime * result + ((rentedPersonLastName == null) ? 0 : rentedPersonLastName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isRented != other.isRented)
			return false;
		if (!Arrays.equals(photo, other.photo))
			return false;
		if (photoAlt == null) {
			if (other.photoAlt != null)
				return false;
		} else if (!photoAlt.equals(other.photoAlt))
			return false;
		if (rentedPersonContactEmail == null) {
			if (other.rentedPersonContactEmail != null)
				return false;
		} else if (!rentedPersonContactEmail.equals(other.rentedPersonContactEmail))
			return false;
		if (rentedPersonContactNumber == null) {
			if (other.rentedPersonContactNumber != null)
				return false;
		} else if (!rentedPersonContactNumber.equals(other.rentedPersonContactNumber))
			return false;
		if (rentedPersonFirstName == null) {
			if (other.rentedPersonFirstName != null)
				return false;
		} else if (!rentedPersonFirstName.equals(other.rentedPersonFirstName))
			return false;
		if (rentedPersonLastName == null) {
			if (other.rentedPersonLastName != null)
				return false;
		} else if (!rentedPersonLastName.equals(other.rentedPersonLastName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", isRented=");
		builder.append(isRented);
		builder.append(", photo=");
		builder.append(Arrays.toString(photo));
		builder.append(", photoAlt=");
		builder.append(photoAlt);
		builder.append(", rentedPersonFirstName=");
		builder.append(rentedPersonFirstName);
		builder.append(", rentedPersonLastName=");
		builder.append(rentedPersonLastName);
		builder.append(", rentedPersonContactNumber=");
		builder.append(rentedPersonContactNumber);
		builder.append(", rentedPersonContactEmail=");
		builder.append(rentedPersonContactEmail);
		builder.append("]");
		return builder.toString();
	}

}
