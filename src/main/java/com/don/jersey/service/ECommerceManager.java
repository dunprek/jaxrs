package com.don.jersey.service;

import com.don.jersey.common.Constants;
import com.don.jersey.model.User;
import com.don.jersey.service.exception.ECommerceManagerException;
import com.don.jersey.service.exception.RepositoryException;
import com.don.jersey.service.repository.UserRepository;





public final class ECommerceManager implements  UserRepository
{
    private final UserRepository userRepository;

    public ECommerceManager(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    public User addUser(User user)
    {
        if(user.getId() == Constants.EMPTY_ID)
        {
            try
            {
                return userRepository.addUser(user);
            }
            catch(RepositoryException e)
            {
                throw new ECommerceManagerException("Could not add user", e);
            }
        }
        throw new ECommerceManagerException("User already added, use updateUser instead");
    }

    public User getUserById(long id)
    {
        try
        {
            return userRepository.getUserById(id);
        }
        catch(RepositoryException e)
        {
            throw new ECommerceManagerException("Could not get user with id: " + id, e);
        }
    }

    public User getUserByEmail(String email)
    {
        try
        {
            return userRepository.getUserByEmail(email);
        }
        catch(RepositoryException e)
        {
            throw new ECommerceManagerException("Could not get user with email: " + email, e);
        }
    }

    public User deleteUser(long id)
    {
        try
        {
            return userRepository.deleteUser(id);
        }
        catch(RepositoryException e)
        {
            throw new ECommerceManagerException("Could not inactivate user with id: " + id, e);
        }
    }

    public User updateUser(User user)
    {
        try
        {
            return userRepository.updateUser(user);
        }
        catch(RepositoryException e)
        {
            throw new ECommerceManagerException("Could not update user with id: " + user.getId(), e);
        }
    }
}
