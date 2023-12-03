import React from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import './App.css'
import Navbar from './components/Navbar'
import Home from './pages'
import About from './pages/about'
import Projects from './pages/projects'
import Qualifications from './pages/qualifications'
import Workers from './pages/workers'

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/about' element={<About />} />
        <Route path='/workers/*' element={<Workers />} />
        <Route path='/projects/*' element={<Projects />} />
        <Route path='/qualifications/*' element={<Qualifications />} />
      </Routes>
    </Router>
  )
}

export default App