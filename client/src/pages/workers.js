import { useEffect, useState } from 'react'
import Select from 'react-select'
import makeAnimated from 'react-select/animated'
import ClickList from '../components/ClickList'
import LocationID from '../utils/location'
import { clickListStyle, darkGrayContainerStyle, grayContainerStyle,pageStyle } from '../utils/styles'
import { getQualifications, getWorkers, createWorker } from '../services/dataService'

const animatedComponents = makeAnimated();

const Worker = (Worker, active) => {
    return (
        <div>
            <div>{Worker.name}</div>
            {active === true ? WorkerBody(Worker) : null}
        </div>
    )
}

const WorkerBody = (Worker) => {
    return (
        <div style={grayContainerStyle}>
            <div style={clickListStyle}><b>Salary:</b> ${Worker.salary}</div>
            <div style={clickListStyle}><b>Workload Value:</b> {Worker.workload}</div>
            <div style={clickListStyle}>
                <b>Qualifications:</b> <ClickList list={Worker.qualifications} styles={darkGrayContainerStyle} path="/qualifications" />
            </div>
            <div style={clickListStyle}>
                <b>Projects:</b> <ClickList list={Worker.projects} styles={darkGrayContainerStyle} path="/projects" />
            </div>
        </div>
    )
}

const qualsDescription = (quals) => {
    const qualOptions = [];

    for(let i = 0; i < quals.length; i++){
        qualOptions.push({value: quals[i].description, label: quals[i].description});
    }

    return qualOptions;
}

const CreateWorkerForm = (props) => {
    const { setworkers } = props
    const [quals, setQualifications] = useState([])
    const [selectedQuals, setSelectedQuals] = useState([]);

    const handleQualsChange = (selectedOptions) => {
        setSelectedQuals(Array.isArray(selectedOptions) ? selectedOptions : []);
    };

    useEffect(() => {
        getQualifications().then((quals) => {
            setQualifications(quals)
        })

    }, [])

    return(
        <div className="card">
            <div className="card-body">
                <form>
                    <div className="form-group">
                        <input 
                            id="name"
                            type="text"
                            className="form-control"
                            placeholder="Enter the worker name..."
                        /><br />

                        <input
                            id="salary"
                            type="number"
                            className="form-control"
                            placeholder="Enter the worker salary..."
                            min="0"
                            step=".01"
                        /><br />                    
                        
                        <Select
                            id="quals"
                            placeholder="Select the worker qualifications..."
                            options={qualsDescription(quals)}
                            value={selectedQuals}
                            onChange={handleQualsChange}
                            isSearchable
                            isMulti
                            components={animatedComponents}
                        /><br />

                        <button type="button"
                            onClick={() => {
                                const name = document.getElementById("name").value
                                const salary = document.getElementById("salary").value
                                const quals = selectedQuals.map(x=>x.value)
                                if (name && salary && quals){
                                    createWorker(name, quals, salary).then(response => {
                                        if (response?.data === 'OK') {
                                            document.getElementById("name").value = ''
                                            document.getElementById("salary").value = ''
                                            setSelectedQuals([])
                                            getWorkers().then(setworkers)
                                        }
                                    })
                                }
                                
                                if (!name && !salary || !name && quals.length == 0 || !salary && quals.length == 0){
                                    window.alert("You must enter valid information for the worker.")
                                }

                                else if (!name){
                                    window.alert("You must enter a valid name for the worker.")
                                }

                                else if (!salary){
                                    window.alert("You must enter a valid salary for the worker.")
                                }

                                else if (quals.length == 0){
                                    window.alert("You must enter at least one valid qualification for the worker.")
                                }
                            }}>
                        Create Worker
                        </button>
                    </div>  
                </form>
            </div>
        </div>
    )
}

const Workers = () => {
    const [workers, setworkers] = useState([])
    useEffect(() => { getWorkers().then(setworkers) }, [])
    const active = LocationID('workers', workers, 'name')
    return (
        <div style={pageStyle}>
            <h2>Create a New Worker:</h2>
            <CreateWorkerForm setworkers={setworkers}/>
            <br/>
            <h2>Click on a worker below to view its details.</h2>
            <ClickList active={active} list={workers} item={Worker} path='/workers' id='name' />
        </div>
    )
}



export default Workers