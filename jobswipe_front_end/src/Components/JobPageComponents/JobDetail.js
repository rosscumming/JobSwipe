import React from "react";
import "./JobDetail.css";

const JobDetail = ({ job }) => {

  return (
    <main className="job-main">
      <section className="job-top">
        <h2>{job.employerName}</h2>
        <h2>{job.jobTitle} </h2>
        <h4>({job.locationName})</h4>
      </section>
      <section className="job-bottom-right">
        <h3>
          £{job.minimumSalary} <br />
          to
          <br /> £{job.maximumSalary}
        </h3>
      </section>
      <section className="job-bottom-left">
        <p> {job.jobDescription} </p>
        <a href={job.jobUrl} target="_blank"><h4>Want to learn more? Click here to see the full advert</h4></a>
      </section>
    </main>
  );
};

export default JobDetail;
