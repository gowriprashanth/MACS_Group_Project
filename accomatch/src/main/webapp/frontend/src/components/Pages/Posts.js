import React , { useEffect, useState } from "react";
import axios from 'axios';
import './Posts.css';

export const Posts = () => {

  const [posts, setPosts] =useState([]);

  useEffect(() => {
    loadPosts();
  },[])

  const loadPosts =async() => {
    const result = await axios.get("http://localhost:8080/api/leaseowner/dashboard/get/list/post")
   console.log(result.data)
    setPosts(result.data)
  }

  return (
    <div className="dashboard-container">
      
      <table className="post-table">
        <thead>
          <tr>
            <th>Sr. no</th>
            <th>Title</th>
            <th>Sub title</th>
            <th>Image</th>

          </tr>
        </thead>
        <tbody>
          {posts.map((post, index) => (
            <tr key={index}>
              {index + 1}
              <td>{post.title}</td>
              <td>{post.subtitle}</td>
              <td>
                <img src={post.document} alt={`Post ${post.title}`} className="post-image" />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
