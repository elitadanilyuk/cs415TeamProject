import { NavLink as Link } from 'react-router-dom'
import styled from 'styled-components'

export const Nav = styled.nav`
  font-size: 2vw;
  font-family: sans-serif;
  background: #5D82A5;
  display: flex;
  justify-content: space-between;
`

export const NavMenu = styled.div`
  display: flex;
  align-items: center;
`

export const NavLogo = styled.div`
  color: #36393B;
  padding: 0 1vw;
  display: flex;
  align-items: center;
  white-space: nowrap;
  &.hover {
    color: #FFFCF7;
  };
`

export const NavLink = styled(Link)`
  color: #C1E4E7;
  display: flex;
  align-items: center;
  text-decoration: none;
  padding: 0 1vw;
  height: 100%;
  cursor: pointer;
  &:hover {
    color: #E9FDFF;
  };
  &.active {
    color: #FFFCF7;
  };
`