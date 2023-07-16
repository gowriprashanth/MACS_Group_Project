import React, { useEffect, useState } from "react";
import axios from 'axios';
import './ApplicantPosts.css';
import { Link, useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';

export const ApplicantPosts = () => {
    const {user_Id} = useParams();
    const [posts, setPosts] = useState([]);
    const [selectedPost, setSelectedPost] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const navigate = useNavigate();


    useEffect(() => {
        const fetchPostDetails = async () => {
            try {
                const postResponse = await axios.get(`http://localhost:8080/api/leaseowner/loggedinapplicant/get/list/applicant/${user_Id}`);
                setPosts(postResponse.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchPostDetails().then(r => console.log("Axios error"));
    }, [user_Id]);


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
                            <img src={post.document} alt={`Post ${post.title}`}/>
                        </div>
                        <div className="post-details">

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
        </div>)
}