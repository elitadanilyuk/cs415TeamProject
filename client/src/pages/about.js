import { pageStyle } from '../utils/styles'

const About = () => {
    return (
        <div style={pageStyle}>
            <div style={{ 'text-align': 'center' }}>
                <h1>Company Project Management</h1>
                <br />
                <h3>
                    This is the software engineering project created by T05 during the Spring 2023 CS415 Software Testing course at Colorado State University.
                </h3>
                <br/>
                <p>
                    The development team consisted of Ben Jurenka, Freya LaLuna, Elita Danilyuk, Marti Selig, and Nicholas Chaffee.
                    This team of agile developers were a well-oiled machine, working seamlessly together to deliver a high-quality software product.
                    They communicated effectively, with frequent stand-up meetings and regular updates on progress. 
                    Each team member collaborated closely to ensure that all components of the software were integrated seamlessly.
                    The team was highly adaptable, able to respond quickly to changing requirements and pivot when needed. 
                    Their strong teamwork and efficient communication allowed them to deliver exceptional results within tight deadlines.
                </p>
            </div>
        </div>
    )
}

export default About