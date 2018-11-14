import React from 'react';

class App extends React.Component {
    constructor() {
        super();
        this.state = {dupItems: [], noneDupItems : []}
    }

    fetchUsers() {
        fetch("http://localhost:8080/duplicateUsers")
            .then(response => response.json())
            .then(data =>
                this.setState({
                    dupItems: data,
                })
            )
            .catch(error => this.setState({ error }));

        fetch("http://localhost:8080/noneDuplicateUsers")
        // get the API response / receive data in JSON format
            .then(response => response.json())
            .then(data =>
                this.setState({
                    noneDupItems: data,
                })
            )
            .catch(error => this.setState({ error }));
    }

    componentWillMount() {

        this.fetchUsers();
    }

    render() {
        let itemsDup = this.state.dupItems;
        let itemsNoneDup = this.state.noneDupItems;



        //id,first_name,last_name,company,email,address1,address2,zip,city,state_long,state,phone

        return (
            <div>
                <h4>*****************************Duplicate List***********************************</h4>
                {itemsDup.map(item => <h4 key = {item.id}>{item.id}--
                    {item.first_name}   {item.last_name}  {item.company}   {item.email}  {item.address1}    {item.address2}     {item.zip}     {item.city}
                    {item.state_long}    {item.state}    {item.phone}

                </h4>)}

                <h4>******************************None Duplicate List******************************</h4>
                {itemsNoneDup.map(item => <h4 key = {item.id}>{item.id}--
                    {item.first_name}   {item.last_name}  {item.company}   {item.email}  {item.address1}    {item.address2}     {item.zip}     {item.city}
                    {item.state_long}    {item.state}    {item.phone}

                </h4>)}

            </div>
        )
    }
}

export default App


