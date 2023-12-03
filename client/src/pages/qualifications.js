import { useEffect, useState } from 'react'
import ClickList from '../components/ClickList'
import { createQualification, getQualifications } from '../services/dataService'
import LocationID from '../utils/location'
import { grayContainerStyle, darkGrayContainerStyle, pageStyle, clickListStyle } from '../utils/styles'

const Qualification = (qualification, active, extraProps) => {
    return (
        <div>
            <div>{qualification.description}</div>
            {active === true ? QualificationBody(qualification, extraProps) : null}
        </div>
    )
}

const QualificationBody = (qualification) => {
    
    return (
        <div>
            <div style={grayContainerStyle}>
                <div style={clickListStyle}><b>Description:</b> {qualification.description}</div>
                <div style={clickListStyle}>
                    <b>Workers:</b>
                    {qualification.workers.length === 0 ? <div>-</div> :
                        <ClickList list={qualification.workers} styles={darkGrayContainerStyle} path="/workers"/>}
                </div>
                
                
            </div>
        </div>
    )
}

const CreateQualificationForm = (props) => {
    const { setQualifications } = props
    return (
        <div className="card">
            <div className="card-body">
                <form>
                    <div className="form-group">
                        <input type="text" className="form-control" id="qualDesc" placeholder="Enter the qualification description..." />
                    </div>
                    <button
                        type="button"
                        onClick={() => {
                            const description = document.getElementById('qualDesc').value
                            if (description) {
                                createQualification(description).then(response => {
                                    if (response?.data === 'OK') {
                                        getQualifications().then(setQualifications)
                                        document.getElementById('qualDesc').value = ""
                                    }
                                })
                            }
                            if (!description){
                                window.alert("You must enter a valid description for the qualification.")
                            }
                        }}
                    >
                        Create Qualification
                    </button>
                </form>
            </div>
        </div>
    )
}

const Qualifications = () => {
    const [qualifications, setQualifications] = useState([])
    useEffect(() => { getQualifications().then(setQualifications) }, [])
    const active = LocationID('qualifications', qualifications, 'description')
    return (
        <div style={pageStyle}>
            <h2>Create a New Qualification:</h2>
            <CreateQualificationForm setQualifications={setQualifications} />
            <br/>
            <h2>Click on a qualification below to view its details.</h2>
            <ClickList active={active} list={qualifications} item={Qualification} path='/qualifications' id='description' />
        </div>
    )
}




export default Qualifications