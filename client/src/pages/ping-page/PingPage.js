import React from 'react';
import { Button, Grid, Header } from 'semantic-ui-react';
import { pingServer } from '../../apis/StarterClient';

class PingPage extends React.Component {

    state = {
        response: null,
        number: 0
    }

    pingServer = async () => {
        this.setState({ response: null });

        const body = { message: 'Hello, server!', number: this.state.number };

        const response = await pingServer(body);

        this.setState({ response: response.data, number: this.state.number + 1 });
    }

    render() {
        return (
            <Grid>
                <Grid.Column width={8}>
                    <p>
                        <Button onClick={this.pingServer}>Ping</Button>
                    </p>
                </Grid.Column>
                <Grid.Column width={8}>
                    <Header>State</Header>
                    <pre>{JSON.stringify(this.state, null, 2)}</pre>
                </Grid.Column>
            </Grid>
        );
    }
}

export default PingPage;
