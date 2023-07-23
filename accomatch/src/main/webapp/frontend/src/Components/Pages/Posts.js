import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import React, { useEffect, useState } from "react";
import axios from 'axios';
import './Posts.css';
import {  useNavigate } from 'react-router-dom';



export const Posts = () => {
  const [posts, setPosts] = useState([]);
  const [ratings,setRatings] = useState([]);
  const [selectedPost, setSelectedPost] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isFilterOpen, setIsFilterOpen] = useState(false);
  const [filter, setFilter] = useState({
    gender: {
      male: false,
      female: false,
    },
    food: {
      vegetarian: false,
      nonVegetarian: false,
    },
    age: ''
  });
  const navigate = useNavigate();
  
  useEffect(() => {
    loadPosts();
  }, []);

  const loadPosts = async () => {
    try {
      const authToken = sessionStorage.getItem("token"); //  authentication token
  
      const postResponse = await axios.get("/api/leaseholder/dashboard/get/list/post", {
        headers: {
          'Authorization': `Bearer ${authToken}` // Include the authentication token in the headers
        }
      });
  
      setPosts(postResponse.data);
      console.log(postResponse.data);
  
      const ratingResponse = await axios.get("/api/reviews/getAllAverageRatings", {
        headers: {
          'Authorization': `Bearer ${authToken}` // Include the authentication token in the headers
        }
      });
  
      setRatings(ratingResponse.data);
      console.log(ratingResponse.data);
    } catch (error) {
      console.error(error);
      toast.error('An error occurred while loading posts. Please try again.', {
        position: toast.POSITION.TOP_RIGHT
      });
    }
  }
  


  const handleDetailsClick = (postId) => {
    navigate(`/posts/${postId}`);
  };

  const openModal = (post) => {
    setSelectedPost(post);
    setIsModalOpen(true);
  }

  const closeModal = () => {
    setIsModalOpen(false);
  }

  const handleCheckboxChange = (event) => {
    const { name, checked } = event.target;
    const filterType = name.split('-')[0];
    const option = name.split('-')[1];

    setFilter((prevFilter) => ({
      ...prevFilter,
      [filterType]: {
        ...prevFilter[filterType],
        [option]: checked ? 1 : 0,
      },
    }));
  };

  const handleAgeChange = (event) => {
    const { value } = event.target;
    setFilter(prevFilter => ({
      ...prevFilter,
      age: value
    }));
  };

  const handleFilterSubmit = async (event) => {
    event.preventDefault();
  
    const genderValue = filter.gender.male ? 1 : 0;
    const foodValue = filter.food.vegetarian ? 1 : 0;
    const ageValue = filter.age;
  
    try {
      const authToken = sessionStorage.getItem("token"); // Replace with the actual authentication token
  
      const response = await axios.post("/api/applicant/posts/filter", {
        Male: genderValue,
        Female: filter.gender.female ? 1 : 0,
        Veg: foodValue,
        NonVeg: filter.food.nonVegetarian ? 1 : 0,
        age: ageValue
      }, {
        headers: {
          'Authorization': `Bearer ${authToken}` // Include the authentication token in the headers
        }
      });
      setPosts(response.data);
    } catch (error) {
      console.error(error);
    }
  };
  
  const toggleFilter = () => {
    setIsFilterOpen(prevValue => !prevValue);
  };

  const toggleFilterOptions = () => {
    setIsFilterOpen(true);
  };

  return (
    <div className="dashboard-container">
      <button onClick={toggleFilterOptions}>Filter</button>
      <div className={`filter-container ${isFilterOpen ? 'open' : ''}`}>
        <div className="filter-header" onClick={toggleFilter}>
          <h3>Filter:</h3>
          <div className="filter-toggle">
            <span className="toggle-icon">{isFilterOpen ? '▲' : '▼'}</span>
          </div>
        </div>
        <form onSubmit={handleFilterSubmit}>
          <div className="filter-content">
            <div className="filter-row">
              <label>Gender:</label>
              <div className="checkbox-group">
                <div>
                  <input
                    type="checkbox"
                    name="gender-male"
                    checked={filter.gender.male}
                    onChange={handleCheckboxChange}
                  />
                  <label>Male</label>
                </div>
                <div>
                  <input
                    type="checkbox"
                    name="gender-female"
                    checked={filter.gender.female}
                    onChange={handleCheckboxChange}
                  />
                  <label>Female</label>
                </div>
              </div>
            </div>
            <div className="filter-row">
              <label>Food:</label>
              <div className="checkbox-group">
                <div>
                  <input
                    type="checkbox"
                    name="food-vegetarian"
                    checked={filter.food.vegetarian}
                    onChange={handleCheckboxChange}
                  />
                  <label>Vegetarian</label>
                </div>
                <div>
                  <input
                    type="checkbox"
                    name="food-nonVegetarian"
                    checked={filter.food.nonVegetarian}
                    onChange={handleCheckboxChange}
                  />
                  <label>Non Vegetarian</label>
                </div>
              </div>
            </div>
            <div className="filter-row">
              <label>Age:</label>
              <input
                type="number"
                name="age"
                value={filter.age}
                onChange={handleAgeChange}
              />
            </div>
          </div>
          <button type="submit">Apply Filter</button>
        </form>
      </div>
      <div className="container">
      <div className="post-list">
        {posts.map((post, index) => (
          <div className="post" key={index}>
            <div className="post-image">
              <img src={post.document} alt={`Post ${post.title}`} />
            </div>
            <div className="post-details"
            onClick={()=>openModal(post)}>
              <h3>{post.title}</h3>
              <p>{post.subtitle}</p>
              <p>Address: {post.address}</p>
              <p>City: {post.city}</p>
              <p>Rent: {post.rent}</p>
              <p>Room Type: {post.roomType}</p>
              <p>Area: {post.area} sqft</p>
              <p>Available From: {post.availableFrom}</p>
            </div>
          </div>
        ))}
      </div>
      {isModalOpen && (
        <div className="custom-modal">
          <div className="modal-content">
            <h3>{selectedPost.title}</h3>
            <p>{selectedPost.subtitle}</p>
            <p>Address: {selectedPost.address}</p>
            <p>City: {selectedPost.city}</p>
            <p>Rent: {selectedPost.rent}</p>
            <p>Room Type: {selectedPost.roomType}</p>
            <p>Area: {selectedPost.area} sqft</p>
            <p>Available From: {selectedPost.availableFrom}</p>
            <button onClick={() => handleDetailsClick(selectedPost.leaseholderApplicationId)}>More Details</button>
            <button onClick={closeModal}>Close</button>
          </div>
        </div>
      )}

      <div className="post-list">
        {ratings.map((rate, index) => (
            <div className="post" key={index}>
              <div className="rating-details">
                <h3>Average Rating: {rate.averageRating}*</h3>
                <p>5 star: {rate.count5Ratings}</p>
                <p>4 star: {rate.count4Ratings}</p>
                <p>3 star: {rate.count3Ratings}</p>
                <p>2 star: {rate.count2Ratings}</p>
                <p>1 star: {rate.count1Ratings}</p>
              </div>
            </div>
        ))}
      </div>
      </div >
    </div>
);
};
