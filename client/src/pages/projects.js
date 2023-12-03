import Select from 'react-select'
import LocationID from '../utils/location'
import ClickList from '../components/ClickList'
import makeAnimated from 'react-select/animated'

import { useEffect, useState } from 'react'
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap'
import { darkGrayContainerStyle, grayContainerStyle, pageStyle, missingStyle, notMissingStyle, clickListStyle } from '../utils/styles'
import { createProject, getProjects, getQualifications, getWorkers, assignWorker, unasignWorker, startProject, finishProject  } from '../services/dataService'

const animatedComponents = makeAnimated();

const Project = (project, active, extraProps) => {
    return(
        <div>
            <div>{project.name}</div>
            {active === true ?  ProjectBody(project, extraProps)  : null}
        </div>
    )
}

const ProjectBody = (project, extraProps) => {
    const {assignDropdownOpen, setAssignDropdownOpen, unassignDropdownOpen, setUnassignDropdownOpen, workers, setprojects} = extraProps;
    return(
        <div>
            <div style={grayContainerStyle}>
                <div style={clickListStyle}><b>Size:</b> {project.size}</div>
                <div style={clickListStyle}><b>Status:</b> {project.status}</div>
                <div style={clickListStyle}>
                    <b>Assigned Employees:</b> 
                    {project.workers.length === 0 ? <div></div> : <ClickList list={project.workers} styles={darkGrayContainerStyle} path="/workers" />}
                </div>
                <div style={clickListStyle}>
                    <b>Qualifications:</b>
                    <ClickList list={project.missingQualifications} styles={missingStyle} path="/qualifications"/>
                    <ClickList list={greenQuals(project)} styles={notMissingStyle} path="/qualifications"/>
                </div>
            </div>
            
            <div className='parent'>
                {((project.missingQualifications.length === 0) || project.status === "FINISHED") ? <div></div> : 
                    <div className='child'>
                        <Dropdown isOpen={assignDropdownOpen} toggle={(e) => {
                            e.stopPropagation();
                            setAssignDropdownOpen(prevState => !prevState);
                        }}>
                            <DropdownToggle caret>
                                Assign Worker
                            </DropdownToggle>
                            <DropdownMenu>
                                {assignList(project, workers)?.map((worker, idx) => <DropdownItem
                                    key={idx}
                                    onClick={() => {
                                        assignWorker(worker, project.name).then((response) => getProjects().then(setprojects))
                                    }}
                                >
                                    {worker}
                                </DropdownItem>
                                
                                )}
                            </DropdownMenu>
                        </Dropdown>    
                    </div>}

                {project.workers.length === 0 ? <div></div> : 
                    <div className='child'>
                        <Dropdown isOpen={unassignDropdownOpen} toggle={(e) => {
                            e.stopPropagation();
                            setUnassignDropdownOpen(prevState => !prevState)
                        }}>
                            <DropdownToggle caret>
                                Unassign Worker
                            </DropdownToggle>
                            <DropdownMenu>
                                {project.workers?.map((worker, idx) => <DropdownItem
                                    key={idx}
                                    onClick={() => {
                                        unasignWorker(worker, project.name).then((response) => getProjects().then(setprojects))
                                    }}
                                >
                                    {worker}
                                </DropdownItem>

                                )}
                            </DropdownMenu>
                        </Dropdown>
                    </div>}
            </div>

            {project.missingQualifications.length !==  0  ||  project.status.toString() !== "PLANNED" ? <div></div> :
            <div>
                <button
                    type="button"
                    onClick={(e) => {
                        e.stopPropagation();
                        console.log("Clicked Start");
                        console.log(project.name);
                        startProject(project.name).then((response) => getProjects().then(setprojects))
                    }}
                >
                    Start Project
                </button>
            </div>}
            {project.status.toString() !== "ACTIVE" ? <div></div> : 
                <div>
                    <button
                        type="button"
                        onClick={(e) => {
                            e.stopPropagation();
                            console.log
                            ("Clicked Finish");
                            console.log(project.name);
                            finishProject(project.name).then((response) => getProjects().then(setprojects))
                        }}
                    >
                    Finish Project    
                    </button>
                </div>}
        </div>
    )
}

const greenQuals = (project) => {
    const quals = project.qualifications;
    const missingQuals = project.missingQualifications;
    const greenQuals = [];

    // check if quals contain missing quals
    for (let i = 0; i < quals.length; i++) {
        // if quals do NOT contain missing quals
        if (!missingQuals.includes(quals[i])) {
            // add to greenQuals
            greenQuals.push(quals[i]);
        }
    }

    return greenQuals;
}

const assignList = (project, workers) => {
    const missingQuals = project.missingQualifications;
    const preassignedWorkers = project.workers;
    const eligibleWorkers = new Array();

    for (let i = 0; i < workers.length; i++){  //For each worker, check if they're already assigned, and if they can take the workload
        const thisWorker = workers[i];
        if((preassignedWorkers.indexOf(thisWorker) > -1)
             || (thisWorker.workload === 12)
             || ((thisWorker.workload >= 11) && (project.size === "MEDIUM"))
             || ((thisWorker.workload >= 10) && (project.size === "BIG"))){
                continue;
             }
        eligibleWorkers.push(workers[i]);
    }

    const assignableWorkers = new Array();
    for (let i = 0; i < eligibleWorkers.length; i++){ //Add each worker to the list if they have any of the missing qualifications
        const workerQuals = eligibleWorkers[i].qualifications;
        if(missingQuals.some(r=> workerQuals.indexOf(r) >= 0)){
            assignableWorkers.push(eligibleWorkers[i].name);
        }
    }

    if (assignableWorkers.length == 0){
        assignableWorkers.push("There are no valid workers availble at this time.");
    }

    return assignableWorkers;
}

const qualsDescriptions = (quals) => {
    const qualOptions = [];

    for (let i = 0; i < quals.length; i++) {
        qualOptions.push({value: quals[i].description, label: quals[i].description});
    }

    return qualOptions;
}

const CreateProjectForm = (props) => { 
    const { setprojects } = props
    const [quals, setQualifications] = useState([])
    const [selectedQuals, setSelectedQuals] = useState([]);
    const [selectedSize, setSelectedSize] = useState([]);

    const handleQualsChange = (selectedOptions) => {
        setSelectedQuals(Array.isArray(selectedOptions) ? selectedOptions : []);
    };

    const handleSizeChange = (selectedOption) => {
        setSelectedSize(selectedOption);
    };

    useEffect(() => {
        getQualifications().then((quals) => { 
            setQualifications(quals)
            })
    }, [])
    
    return (
        <div className="card">
            <div className="card-body">
                <form>
                    <div className="form-group">
                        <input type="text" className="form-control" id="name" placeholder="Enter the project name..." /><br/>

                        <Select
                            id="quals"
                            placeholder="Select the project qualifications..."
                            options={qualsDescriptions(quals)}
                            value={selectedQuals}
                            onChange={handleQualsChange}
                            isSearchable
                            isMulti
                            closeMenuOnSelect={false}
                            components={animatedComponents}
                        /><br/>

                        <Select 
                            id="size"
                            placeholder="Select the project size..."
                            options={[
                                { key:'SMALL', value:'SMALL', label: 'SMALL' },
                                { key:'MEDIM', value: 'MEDIUM', label: 'MEDIUM' },
                                { value: 'BIG', label: 'BIG' }
                            ]}
                            value={selectedSize}
                            isSearchable
                            onChange={handleSizeChange}
                        /><br/>

                        <button type="button"
                            onClick={() => {
                                const name = document.getElementById('name').value
                                const size = selectedSize.value
                                const quals = selectedQuals.map(x=>x.value)
                                
                                if(!name && quals.length == 0 || !name && !size || quals.length == 0 && !size){
                                    window.alert("You must enter valid information for the project.")
                                }

                                else if (!name){
                                    window.alert("You must enter a valid name for the project.")
                                }

                                else if (quals.length == 0){
                                    window.alert("You must enter at least one valid qualification for the project.")
                                }

                                else if (!size) {
                                    window.alert("You must enter a valid size for the project.")
                                }

                                if (name && quals && size) {
                                    createProject(name, quals, size).then(response => {
                                        if (response?.data === 'OK') {
                                            setSelectedQuals([])
                                            setSelectedSize('')
                                            document.getElementById('name').value = ''
                                            getProjects().then(setprojects)
                                        }
                                    })
                                }
                            }}
                        >
                            Create Project
                        </button>
                    </div>
                </form>
            </div>
        </div>
    )
}

const Projects = () => {
    const [assignDropdownOpen, setAssignDropdownOpen] = useState(false);
    const [unassignDropdownOpen, setUnassignDropdownOpen] = useState(false);
    // Add this to extraProps and then use to toggle the assign button
    // [assignDropdownOpen, setAssignDropdownOpen] = useState(false);
    const [projects, setprojects] = useState([])
    const [workers, setworkers] = useState([])
    useEffect(() => { getProjects().then(setprojects) }, [])
    useEffect(() => { getWorkers().then(setworkers) }, [])
    const active = LocationID('projects', projects, 'name');
    const extraProps = {
            assignDropdownOpen: assignDropdownOpen,
            unassignDropdownOpen: unassignDropdownOpen,
            setAssignDropdownOpen: setAssignDropdownOpen,
            setUnassignDropdownOpen: setUnassignDropdownOpen,
            workers: workers,
            setprojects: setprojects
        }
    return (
        <div style={pageStyle}>
            <h2>Create a New Project:</h2>
            <CreateProjectForm setprojects={setprojects}/>
            <br/>
            <h2>Click on a project below to view its details.</h2>
            <ClickList active={active} list={projects} item={Project} path='/projects' id='name' extraProps={extraProps} />
        </div>
    )
}

export default Projects