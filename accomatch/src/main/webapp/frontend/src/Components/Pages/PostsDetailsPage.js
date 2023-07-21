import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import './PostsDetailsPage.css';
import "react-responsive-carousel/lib/styles/carousel.min.css";

import { Carousel } from 'react-responsive-carousel';
import { Link, useNavigate } from 'react-router-dom';

export const PostsDetailsPage = () => {
  const navigate =useNavigate();

  const { applicationId } = useParams();
  const [alreadyApplied,setAlreadyApplied] = useState(false);
  const [post, setPost] = useState(null);
  const [ratings,setRatings] = useState([]);
  const [images, setImages] = useState([]);
  const [foodPreferences, setFoodPreferences] = useState([]);
  const [genderPreferences, setGenderPreferences] = useState([]);
  const [reviewResponse, setReviewResponse] = useState([]);
  const [errMsg, setErrMsg] =useState ('');
  const [success, setSuccess] = useState(false);
  const handleReviewClick =()=>{
      navigate(`/ratingform/${applicationId}`)
  }
  const handleApplySubmit =async () => {
    let bodyObj = {
        user_id:sessionStorage.getItem("user_id"),
        application_id:applicationId,
        status:"Pending"
    }

    fetch("http://localhost:8080/api/applicant/apply", {
        method: "POST",
        headers: { "Content-Type": "application/json",
        "Authorization": `Bearer ${sessionStorage.getItem("token")}`}, // Include the authentication token in the headers
        body: JSON.stringify(bodyObj),
    })
    .then((response) => {
        console.log(response);
        if(response.status===200){
            navigate("/posts");
        }
        return response.text(); // Read the response data as text
    })
    .then((data) => {
        console.log(data); // Log the response data
        if (data === "success") {
        setSuccess(true);
        } else {
        setErrMsg("Login failed. Please try again."); // Set an appropriate error message
        }
    })
    .catch((error) => {
      console.error(error);
      toast.error('An error occurred while loading posts. Please try again.', {
        position: toast.POSITION.TOP_RIGHT
      });
        setErrMsg("An error occurred. Please try again."); // Set an appropriate error message
    });
    }
  useEffect(() => {
    const isUserAlreadyApplied = async ()=>{
      let bodyObj = {
        user_id:sessionStorage.getItem("user_id"),
        application_id:applicationId
    }
    // setAlreadyApplied(await isUserAlreadyApplied());
    fetch("http://localhost:8080/api/applicant/isApplied", {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${sessionStorage.getItem("token")}` // Include the authentication token in the headers
    },
    body: JSON.stringify(bodyObj),
    
})
.then((response) => {
    console.log(response);
    return response.json(); // Read the response data as JSON
})
.then((data) => {
    console.log(data); // Log the response data
    if (data === "success") {
        setSuccess(true);
    } else {
        setErrMsg("Login failed. Please try again."); // Set an appropriate error message
    }
})
.catch((error) => {
    setErrMsg("An error occurred. Please try again."); // Set an appropriate error message
});
setAlreadyApplied(await isUserAlreadyApplied());
 }
 const fetchPostDetails = async () => {
  console.log(applicationId);
  try {
    const authToken = sessionStorage.getItem("token"); // Retrieve the authentication token from sessionStorage

    const postResponse = await axios.get(`http://localhost:8080/api/leaseowner/dashboard/get/post/details/${applicationId}`, {
      headers: {
        'Authorization': `Bearer ${authToken}` // Include the retrieved authentication token in the headers
      }
    });
    setPost(postResponse.data);
    console.log(postResponse.data);

    const imagesResponse = await axios.get(`http://localhost:8080/api/leaseowner/dashboard/get/list/images/${applicationId}`, {
      headers: {
        'Authorization': `Bearer ${authToken}` // Include the retrieved authentication token in the headers
      }
    });
    setImages(imagesResponse.data);

    const foodPreferencesResponse = await axios.get(`http://localhost:8080/api/leaseowner/dashboard/get/list/food/${applicationId}`, {
      headers: {
        'Authorization': `Bearer ${authToken}` // Include the retrieved authentication token in the headers
      }
    });
    setFoodPreferences(foodPreferencesResponse.data);

    const genderPreferencesResponse = await axios.get(`http://localhost:8080/api/leaseowner/dashboard/get/list/gender/${applicationId}`, {
      headers: {
        'Authorization': `Bearer ${authToken}` // Include the retrieved authentication token in the headers
      }
    });
    setGenderPreferences(genderPreferencesResponse.data);

    const response = await axios.get(`http://localhost:8080/reviews/getListOfAllRatings/${applicationId}`, {
      headers: {
        'Authorization': `Bearer ${authToken}` // Include the retrieved authentication token in the headers
      }
    });
    setReviewResponse(response.data);
    console.log(response.data);

    const ratingResponse = await axios.get(`http://localhost:8080/reviews/getAverageRatings/${applicationId}`, {
      headers: {
        'Authorization': `Bearer ${authToken}` // Include the retrieved authentication token in the headers
      }
    }
    );
    // setAlreadyApplied(await isUserAlreadyApplied());
    setRatings(ratingResponse.data);
    console.log(ratingResponse.data);
  } catch (error) {
    console.log(error.response.data);
    toast.error(error.response.data.message, {
      position: toast.POSITION.TOP_RIGHT
    });
  }
};


    fetchPostDetails();
  }, [applicationId]);

  if (!post) {
    return <div>Loading...</div>;
  }
  const handleApplicantClick = (userId) => {
    navigate(`/leaseapplicantview/${userId}`);
  };
  return (
    <div className="details-container">
      <div className="details-title">
        <h2>{post.title}</h2>
        <h4>{post.subtitle}</h4>
      </div>

      <h3>Images</h3>
      <div className="carousel-container">
        <Carousel>
          {images.map((image, index) => (
            <div key={index}>
              <img src={image} alt={`Image ${index + 1}`} />
            </div>
          ))}
        </Carousel>
      </div>

      <div className="details-section">
        <h3>Details</h3>
        <div className="details-list-container">
          <ul>
            <li>
              <span className="details-label">Address:</span> {post.address}
            </li>
            <li>
              <span className="details-label">Location:</span> {post.locationCity}
            </li>
            <li>
              <span className="details-label">Size:</span> {post.size}
            </li>
            <li>
              <span className="details-label">Room Type:</span> {post.roomType}
            </li>
            <li>
              <span className="details-label">Rent:</span> {post.rent}
            </li>
            <li>
              <span className="details-label">Other Preferences:</span> {post.otherPreferences}
            </li>
            <li>
              <span className="details-label">Start Date:</span> {post.startDate}
            </li>
          </ul>
        </div>
      </div>

      <div className="preferences-section">
        <h3>Food Preferences</h3>
        <div className="preferences-container">
          {foodPreferences.map((preference, index) => (
            <span key={index} className="preference preference-food">{preference}</span>
          ))}
          
        </div>
      </div>

      <div className="preferences-section">
        <h3>Gender Preferences</h3>
        <div className="preferences-container">
         
          {genderPreferences.map((preference, index) => (
            <span key={index} className="preference preference-gender">{preference}</span>
          ))}
        </div>
      </div>
      {alreadyApplied ?
      {/* Apply button */}
      (<button className="chat-button">Chat</button>):
        (<button className="apply-button" onClick={()=>handleApplySubmit()}>Apply</button>) 
      }

      {/* Applicant button */}
      {/*<button onClick={() => handleApplicantClick(post.leaseholderApplicationId)}>Applicant</button>*/}
      {/*/!* Review button *!/*/}
      <button onClick={() => handleReviewClick(post.application_id)}>Review the post</button>
        <div >

            <h3>Reviews and Ratings</h3>
            <div className="preferences-section">
                {ratings.map((rate, index) => (
                    <div className="preferences-section" key={index}>
                        <div className="preferences-section">
                            <h3>Average rating: {rate.averageRating}</h3>
                        </div>
                    </div>
                ))}
            </div>

            <div >

                        {reviewResponse.map((review, index) => (

                            <div className="preferences-section" key={index}>
                                    <p> </p>
                                    <p>@{review.name}</p>
                                    <p>Rating: {review.rating}</p>
                                    <p>Comment: {review.comment}</p>
                                </div>
                        ))}
            </div>
        </div>



    </div>

  );
};
