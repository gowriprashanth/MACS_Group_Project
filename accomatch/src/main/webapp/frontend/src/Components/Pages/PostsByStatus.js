import React, { useEffect, useState } from "react";
import axios from 'axios';
import './PostsByStatus.css';
import { Link, useNavigate } from 'react-router-dom';

export const PostsByStatus = () => {
    console.log("in")
  const [posts, setPosts] = useState([]);
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
    age: '',
    status: ''
  });
  const navigate = useNavigate();
  
  useEffect(() => {
    loadPosts();
  }, []);

  const loadPosts = async () => {
    try {
      const authToken = sessionStorage.getItem("token"); // authentication token
      
      const response = await axios.get("http://localhost:8080/admin/get/list/post", {
        headers: {
          Authorization: `Bearer ${authToken}` //  authentication token in the headers
        }
      });
      setPosts(response.data);
    } catch (error) {
      console.error(error);
    }
  };
  

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

  const handleStatusChange = (event) => {
    const { value } = event.target;
    setFilter(prevFilter => ({
      ...prevFilter,
      status: value
    }));
  };

  const handleFilterSubmit = async (event) => {
    event.preventDefault();
    try {
      const authToken = sessionStorage.getItem("token"); //  authentication token
      
      if (filter.status !== "") {
        const response = await axios.post(
          "http://localhost:8080/admin/get/list/postbystatus",
          {
            status: filter.status
          },
          {
            headers: {
              Authorization: `Bearer ${authToken}` // authentication token in the headers
            }
          }
        );
        setPosts(response.data);
      } else {
        const response = await axios.get("http://localhost:8080/admin/get/list/post", {
          headers: {
            Authorization: `Bearer ${authToken}` // authentication token in the headers
          }
        });
        setPosts(response.data);
      }
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
          <label>Status:</label>
          <select
            name="status"
            value={filter.status}
            onChange={handleStatusChange}>
            <option value="">All</option>
            <option value="0">Pending</option>
            <option value="1">Approved</option>
            <option value="-1">Rejected</option>
          </select>
        </div>
          </div>
          <button type="submit">Apply Filter</button>
        </form>
      </div>
      <div className="post-list">
        {posts.map((post, index) => (
          <div className="post" key={index}>
            <div className="post-image">
              <img src={post.document} alt={`Post ${post.title}`} />
            </div>
            <div className="post-details">
              <h3 onClick={() => openModal(post)}>{post.title}</h3>
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
    </div>
  );
};
