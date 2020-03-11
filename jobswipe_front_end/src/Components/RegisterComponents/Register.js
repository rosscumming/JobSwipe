import React, { Component } from "react";
import { Link } from 'react-router-dom';
import './Register.css'

class Register extends Component {

  constructor(props) {
    super(props);
    this.state = {
      name: "",
      salary: "",
      initial_salary: 0,
      salary_weight: 5,
      location: ""
    }
    this.handleNewUser = this.handleNewUser.bind(this)
    this.handleNameChange = this.handleNameChange.bind(this)
    this.handleSalaryChange = this.handleSalaryChange.bind(this)
    this.handleLocationChange = this.handleLocationChange.bind(this)
    this.isFormValid = this.isFormValid.bind(this)
  }

  handleNewUser(event){
    if (!this.state.name || !this.state.salary || !this.state.location) return null;
    const newUser = {
      name: this.state.name,
      salary: this.state.salary,
      initial_salary: this.state.salary,
      salary_weight: this.state.salary_weight,
      location: this.state.location
    }
    this.props.onUserSubmit(newUser)
  }

  handleNameChange(event){
    this.setState({ name: event.target.value})
  }

  handleSalaryChange(event) {
    this.setState({ salary: event.target.value})
  }

  handleLocationChange(event) {
    this.setState({ location: event.target.value})
  }

  isFormValid() {
    return this.state.name && this.state.salary && this.state.location ? './jobs' : './register'
  }

  render () {
    return(

      <article className="register-article">
        <h1>Registration Page</h1>
        <h3><span>Create a new </span>
          <span className="job-swipe-capitals">J</span>
          <span className="job-swipe">ob</span>
          <span className="job-swipe-capitals">S</span>
          <span className="job-swipe">wipe</span>
          <span> profile here!!</span>
        </h3>

        <form className="register-form">
          <input type="text"
            placeholder="Your Name"
            value={this.state.name}
            onChange={this.handleNameChange}
            className="register-input"
          />

          <input type="number"
            step='2000'
            placeholder="Expected Salary"
            value={this.state.salary}
            onChange={this.handleSalaryChange}
            className="register-input"
          />

          <input type="text"
            placeholder="Location"
            value={this.state.location}
            onChange={this.handleLocationChange}
            className="register-input"
          />

          <Link to={this.isFormValid} onClick={this.handleNewUser}>
            <button type="submit" className="register-btn">Register User</button>
          </Link>
      </form>

    </article>
    )


  }











}

export default Register;
