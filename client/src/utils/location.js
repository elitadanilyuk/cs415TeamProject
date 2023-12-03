import { useMatch } from 'react-router-dom'

const LocationID = (path, data, id) => {
    const match = useMatch(path + '/:id')
    if (match && match.params && match.params.id)
        return data.findIndex((obj) => obj[id] === match.params.id)
    return -1
}

export default LocationID