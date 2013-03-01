package controller;

import parser.Nodes.CommandNode;
import parser.Nodes.exceptions.InvalidArgumentsException;

public interface IParserProvider {
    
    public void addCommand (String command, CommandNode com);
    
    public CommandNode getCommand (String command);
    
    public Integer getVariable (String var) throws InvalidArgumentsException;

    public void setVariable (String var, Integer val);
}
