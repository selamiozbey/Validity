package com.validitytest.validity.model;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

@Component
public class User implements IEntity, Serializable,Cloneable,Comparable<User> {
    int id;
    String first_name;
    String last_name;
    String company;
    String email;
    String address1;
    String address2;
    String zip;
    String city;
    String state_long;
    String state;
    String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_long() {
        return state_long;
    }

    public void setState_long(String state_long) {
        this.state_long = state_long;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //id,first_name,last_name,company,email,address1,address2,zip,city,state_long,state,phone


    @Override
    public int compareTo(User o) {
        if(hashCode() == o.hashCode()) return 0;
        return hashCode() == o.hashCode() ? 1 : -1;
    }

    @Override
    public int hashCode() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );


        byte c[] = outputStream.toByteArray( );
//        String str = String.valueOf(id).getBytes() + first_name+last_name+company+email+address1+address2+zip+city+state_long+state+phone;
        try {
            outputStream.write( String.valueOf(id).getBytes() );
            outputStream.write(first_name.getBytes() );
            outputStream.write(last_name.getBytes() );
            outputStream.write(company.getBytes() );
            outputStream.write(email.getBytes() );
            outputStream.write(address1.getBytes() );
            outputStream.write(address2.getBytes() );
            outputStream.write(zip.getBytes() );
            outputStream.write(city.getBytes() );
            outputStream.write(state_long.getBytes() );
            outputStream.write(state.getBytes() );
            outputStream.write(phone.getBytes() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        int result = str.hashCode();
        return result;
    }


    public User clone() {
        User user = null;
        try {
            user =  (User)super.clone();
        }
        catch (CloneNotSupportedException e) {
            System.out.println("CloneNotSupportedException comes out : "+e.getMessage());
        }
        return user;
    }
}
