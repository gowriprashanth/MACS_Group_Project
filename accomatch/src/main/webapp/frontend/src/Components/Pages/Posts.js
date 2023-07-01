import React, { useEffect, useState } from "react";
import axios from 'axios';
import './Posts.css';

export const Posts = () => {
  const [posts, setPosts] = useState([]);
  const [selectedPost, setSelectedPost] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    loadPosts();
  }, []);

  const loadPosts = async () => {
    const result = await axios.get("http://localhost:8080/api/leaseowner/dashboard/get/list/post");
    console.log(result.data);
    setPosts(result.data);
  }

  const openModal = (post) => {
    setSelectedPost(post);
    setIsModalOpen(true);
  }

  const closeModal = () => {
    setIsModalOpen(false);
  }

  const apply = () => {
    // Handle apply logic here
    closeModal();
  }

  return (
    <div className="dashboard-container">
      <div className="post-list">
        {posts.map((post, index) => (
          <div className="post" key={index}>
            <div className="post-image">
              <img src={post.document} alt={`Post ${post.title}`} />
            </div>
            <div className="post-details">
              <h3  onClick={() => openModal(post)}>{post.title}</h3>
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
        <div className="modal">
          <div className="modal-content">
            <h3>{selectedPost.title}</h3>
            <p>{selectedPost.subtitle}</p>
            <p>Address: {selectedPost.address}</p>
            <p>City: {selectedPost.city}</p>
            <p>Rent: {selectedPost.rent}</p>
            <p>Room Type: {selectedPost.roomType}</p>
            <p>Area: {selectedPost.area} sqft</p>
            <p>Available From: {selectedPost.availableFrom}</p>
            <button onClick={apply}>Apply</button>
            <button onClick={closeModal}>Close</button>
          </div>
        </div>
      )}
    </div>
  );
};