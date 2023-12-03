import { Nav, NavLink, NavLogo, NavMenu } from './navbarElements'

const Navbar = () => {
    return (
        <>
            <Nav>
                <NavMenu>
                    <a href="/">
                        <img alt="CSU Ram Logo" src="https://brand.colostate.edu/wp-content/uploads/sites/47/2019/01/CSU-Ram-357-617.png"/>
                    </a>
                    <NavLogo>
                        CS415
                    </NavLogo>
                    <NavLink to="/">
                        Home
                    </NavLink>
                    <NavLink to="/workers">
                        Workers
                    </NavLink>
                    <NavLink to="/projects">
                        Projects
                    </NavLink>
                    <NavLink to="/qualifications">
                        Qualifications
                    </NavLink>
                    <NavLink to="/about">
                        About
                    </NavLink>
                </NavMenu>
            </Nav>
        </>
    )
}

export default Navbar