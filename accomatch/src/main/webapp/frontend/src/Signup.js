import './Signup.css';
import { useRef, useState, useEffect } from 'react';

export const Signup = () => {
  const emailRef = useRef();
  const nameRef = useRef();
  const passwordRef = useRef();
  const ageRef = useRef();
  const genderRef = useRef();
  const mobileRef = useRef();
  const addressRef = useRef();

  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [age, setAge] = useState('');
  const [gender, setGender] = useState('');
  const [mobile, setMobile] = useState('');
  const [address, setAddress] = useState('');
  const [isAdmin, setIsAdmin] = useState(false);
  const [isLeaseOwner, setIsLeaseOwner] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(email, name, password, age, gender, mobile, address, isAdmin, isLeaseOwner);
    // Perform signup logic here
  };

  useEffect(() => {
    emailRef.current.focus();
  }, []);

  return (
    <section>
      <h1>Sign Up</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          ref={emailRef}
          autoComplete="off"
          onChange={(e) => setEmail(e.target.value)}
          value={email}
          required
        />

        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          ref={nameRef}
          autoComplete="off"
          onChange={(e) => setName(e.target.value)}
          value={name}
          required
        />

        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          ref={passwordRef}
          autoComplete="off"
          onChange={(e) => setPassword(e.target.value)}
          value={password}
          required
        />

        <label htmlFor="age">Age:</label>
        <input
          type="number"
          id="age"
          ref={ageRef}
          autoComplete="off"
          onChange={(e) => setAge(e.target.value)}
          value={age}
          required
        />

        <label htmlFor="gender">Gender:</label>
        <select
        id="gender"
        ref={genderRef}
        onChange={(e) => setGender(e.target.value)}
        value={gender}
        >
        <option value="">Select</option>
        <option value="male">Male</option>
        <option value="female">Female</option>
        <option value="other">Other</option>
        </select>

        
        <label htmlFor="mobile">Mobile:</label>
        <input
          type="tel"
          id="mobile"
          ref={mobileRef}
          autoComplete="off"
          onChange={(e) => setMobile(e.target.value)}
          value={mobile}
        />

        <label htmlFor="address">Address:</label>
        <input
          type="text"
          id="address"
          ref={addressRef}
          autoComplete="off"
          onChange={(e) => setAddress(e.target.value)}
          value={address}
        />

        <div>
          <label htmlFor="isAdmin">Is Admin:</label>
          <input
            type="checkbox"
            id="isAdmin"
            checked={isAdmin}
            onChange={(e) => setIsAdmin(e.target.checked)}
          />
        </div>

        <div>
          <label htmlFor="isLeaseOwner">Is Lease Owner:</label>
          <input
            type="checkbox"
            id="isLeaseOwner"
            checked={isLeaseOwner}
           
            onChange={(e) => setIsLeaseOwner(e.target.checked)}
            />
            </div>
            <button>Sign Up</button>
  </form>
</section>
);
};