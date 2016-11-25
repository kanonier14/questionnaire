var CreateQuestionForm = React.createClass({
    getInitialState: function() {
        return({answers: []});
    },

    addAnswer: function() {
        let answersState = this.state.answers;
        let nameAnswer = this.props.name + '_answer_' + answersState.length;
        answersState.push({name: nameAnswer});
        this.setState({answers: answersState});
    },

    render: function() {
        let title_name = this.props.name + '_title';
        let type_name = this.props.name + '_type';
        let answers = this.state.answers.map(function (item) {
            return <AnswerEditRadioButton key={item.name} name={item.name}/>;
        });
        return(
            <div className="question-edit-form">
                <div className="remove_question_button" data-questionname={this.props.name} onClick={this.props.removeQuestion}>X</div>
                <textarea name={title_name} className="question-edit-form__text" placeholder="Введите текст вопроса"></textarea>
                <input type="hidden" name={type_name} value={this.props.type}/>
                <div className="question-edit-form__answers">
                    {answers}
                </div>
                <div className="question-edit-form__add-answer-button" onClick={this.addAnswer}>
                    Добавить ответ
                </div>
            </div>
        );
    }
});