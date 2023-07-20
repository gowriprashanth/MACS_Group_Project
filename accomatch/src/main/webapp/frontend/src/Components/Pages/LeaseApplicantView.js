import React, { useEffect, useState } from "react";
import axios from 'axios';
import './LeaseApplicantView.css';
import { Link, useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';

export const LeaseApplicantView = () => {
    const { user_Id } = useParams();
    const [posts, setPosts] = useState([]);
    const [selectedPost, setSelectedPost] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const navigate = useNavigate();




    useEffect(() => {
        const fetchApplicantDetails = async () => {
            try {
                const authToken = sessionStorage.getItem("token");
                const postResponse = await axios.get(`http://localhost:8080/api/leaseowner/applicant/get/list/applicant/${user_Id}`
                    , {
                        headers: {
                            Authorization: `Bearer ${authToken}`}});
                setPosts(postResponse.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchApplicantDetails().then(r => console.log("Axios error"));
    }, [user_Id]);

    const handleDetailsClick = (userID) => {
        navigate(`/applicant/${user_Id}`);
    };

    const openModal = (applicant) => {
        setSelectedPost(applicant);
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
                {posts.map((applicant, index) => (
                    /*<div className="post" key={index}>
                        <div className="post-image">
                            <img src={post.document} alt={`Post ${post.title}`} />
                        </div>*/
                        <div className="applicant-details">

                            <p>Name: {applicant.name}</p>
                            <p>Email: {applicant.email}</p>
                            <p>Age: {applicant.age}</p>
                            <p>Gender: {applicant.gender}</p>
                            <p>Mobile: {applicant.mobile} </p>

                    </div>
                ))}
            </div>

        </div>
    );
};
