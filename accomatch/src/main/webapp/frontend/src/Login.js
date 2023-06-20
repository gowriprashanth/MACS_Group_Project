import './Login.css';
import {useRef, useState , useEffect} from 'react';
import { Link } from 'react-router-dom'; // Import the Link component from react-router-dom


export const Login =() =>{

    const userRef =useRef();
    const errRef =useRef();

    const [user, setUser]= useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setErrMsg] =useState ('');
    const [success, setSuccess] = useState(false);

    useEffect(() =>{
        userRef.current.focus();
    },[])

    useEffect(() => {
        setErrMsg('');
    }, [user,pwd])

    const handleSubmit =async (e) => {
        e.preventDefault();
        console.log(user,pwd);
        setUser('');
        setPwd('');
        fetch("http://localhosr:8080/login",{
            method: "POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify()
        }).then((response) => {
            if (response.ok) {
              setSuccess(true);
            } else {
              setErrMsg("Login failed. Please try again."); // Set an appropriate error message
            }
          }).catch((error) => {
            setErrMsg("An error occurred. Please try again."); // Set an appropriate error message
          });
        }

    return (
        <>
        {success ? (
            <section>
                <h1>
                    You are logged in!
                </h1>
            <br/>
            <p>
                <a href="#">Go to Home</a>
            </p>

            </section>

        ):(

        
        <section>
            <p ref={errRef} className ={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">
                {errMsg}
            </p>
            <h1>Sign In</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor ="username">Username:</label>
                <input 
                    type="text"
                    id="username"
                    ref={userRef}
                    autoComplete="off"
                    onChange={(e) => setUser (e.target.value)}
                    value={user}
                    required
                />
                 <label htmlFor ="password">Password:</label>
                <input 
                    type="password"
                    id="password"
                    onChange={(e) => setPwd(e.target.value)}
                    value={pwd}
                    required
                />

                <button>Sign In</button>

            </form>
                <p>
                    Need an Account? <br/>
                    <span className="line">
                    {/* Put the reference for the  link */}
                    <a href="/signup">Sign Up</a>
                    </span>
                </p>
        </section>
        )}
        </>
    )
}